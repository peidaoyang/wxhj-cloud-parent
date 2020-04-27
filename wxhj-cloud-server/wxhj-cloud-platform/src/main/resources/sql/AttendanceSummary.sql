-- 函数
CREATE DEFINER=`root`@`%` FUNCTION `fun_get_day_status`(work_status1 int,
work_status2 int, work_status3 int) RETURNS int(11)
    NO SQL
BEGIN

		DECLARE up_late int;
		DECLARE down_early int;
		DECLARE work_status_final int;

			SET up_late = 0;
			SET down_early = 0;
			SET work_status_final = work_status1;

			IF work_status1 IS NULL THEN
				return NULL;
			END IF;
			IF work_status2 IS NULL THEN
				SET work_status2 = 0;
			END IF;
			IF work_status3 IS NULL THEN
				SET work_status3 = 0;
			END IF;

			IF work_status1 = 1 OR work_status2 = 1 OR work_status3 = 1 THEN
				SET up_late = 1;
			END IF;
			IF work_status1 = 2 OR work_status2 = 2 OR work_status3 = 2 THEN
				SET down_early = 1;
			END IF;

			IF work_status2 > work_status1 THEN
				SET work_status_final = work_status2;
			END IF;
			IF work_status3 > work_status_final THEN
				SET work_status_final = work_status3;
			END IF;

			IF work_status_final >= 3 THEN
				RETURN work_status_final;
			ELSEIF up_late = 1 AND down_early = 1 THEN
				RETURN 3;
			END IF;

			RETURN work_status_final;
END;


CREATE DEFINER=`root`@`%` FUNCTION `fun_get_up_down_time`(time1 int,time2 int) RETURNS int(4)
    NO SQL
BEGIN

		IF(time1 IS NULL OR time2 IS NULL) THEN
			-- 不需要打卡（休息等）或没有打卡
			return 0;
		ELSEIF(time2 < 0 OR time1 < 0) THEN
			-- 全时间段请假
			return 0;
		ELSEIF(time2 - time1 > 0) THEN
			return time2 - time1;
		END IF;
			return 0;
END;

CREATE DEFINER=`root`@`%` FUNCTION `fun_get_work_status`(up_matching_time int,
down_matching_time int, up_time int, down_time int) RETURNS int(4)
    NO SQL
BEGIN
			IF up_time < 0 OR down_time < 0 THEN
				return 0;
			END IF;

			IF up_matching_time IS NULL AND down_matching_time IS NULL THEN
				return 6;
			ELSEIF up_matching_time IS NULL AND down_matching_time IS NOT NULL THEN
				return 4;
			ELSEIF up_matching_time IS NOT NULL AND down_matching_time IS NULL THEN
				return 5;

			ELSEIF up_matching_time - up_time > 0 AND down_matching_time - down_time < 0 THEN
				return 3;
			ELSEIF up_matching_time - up_time > 0 AND down_matching_time - down_time >= 0 THEN
				return 1;
			ELSEIF up_matching_time - up_time <= 0 AND down_matching_time - down_time < 0 THEN
				return 2;
			END IF;
				return 0;
END;

CREATE OR REPLACE VIEW view_attendance_summary_matching AS
SELECT a.datetime AS datetime,
			a.authority_group_id AS authority_group_id,
			a.account_id AS account_id,
			a.attendance_type AS attendance_type,
			a.account_name AS account_name,
			a.authority_group_name AS authority_group_name,
			a.organize_id AS organize_id,
			a.child_organize_id AS child_organize_id,
			a.sequence1 AS sequence1,
			a.up_time1 AS up_time1,
			a.down_time1 AS down_time1,
			a.time_state1 AS time_state1,
			a.leave_time1 AS leave_time1,
			a.travel_time1 AS travel_time1,
			a.work_time1 AS work_time1,
			-- 第一班次
			-- 实际上班打卡时间
			bu1.matching_time AS real_up_time1,
			-- 迟到时间，≤0 没有迟到 >0 有迟到
			`fun_get_up_down_time`(a.up_time1, bu1.matching_time) AS up_late_time1,

			-- 实际下班打卡时间
			bd1.matching_time AS real_down_time1,
			-- 早退时间，>0 有早退 ≤0 没有早退
			`fun_get_up_down_time`(bd1.matching_time, a.down_time1) AS down_early_time1,
			-- 打卡状态，0：正常，1：迟到，2：早退，3：迟到早退，4：上班缺卡，5：下班缺卡，6：旷工
			(CASE a.attendance_type
				WHEN 1 THEN
					NULL
				ELSE
					CASE ISNULL(a.sequence1)
						WHEN 0 THEN
							`fun_get_work_status`(bu1.matching_time, bd1.matching_time, a.up_time1, a.down_time1)
						ELSE
							NULL
					END
			END) AS work_status1,

			-- 第二班次
			a.sequence2 AS sequence2,
			a.up_time2 AS up_time2,
			a.down_time2 AS down_time2,
			a.time_state2 AS time_state2,
			a.leave_time2 AS leave_time2,
			a.travel_time2 AS travel_time2,
			a.work_time2 AS work_time2,
			-- 实际上班打卡时间
			bu2.matching_time AS real_up_time2,
			-- 迟到时间，≤0 没有迟到 >0 有迟到
			`fun_get_up_down_time`(a.up_time2, bu2.matching_time) AS up_late_time2,

			-- 实际下班打卡时间
			bd2.matching_time AS real_down_time2,
			-- 早退时间，>0 有早退 ≤0 没有早退
			`fun_get_up_down_time`(bd2.matching_time, a.down_time2) AS down_early_time2,
			-- 打卡状态，0：正常，1：迟到，2：早退，3：迟到早退，4：上班缺卡，5：下班缺卡，6：旷工
			(CASE a.attendance_type
				WHEN 1 THEN
					NULL
				ELSE
					CASE ISNULL(a.sequence2)
						WHEN 0 THEN
							`fun_get_work_status`(bu2.matching_time, bd2.matching_time, a.up_time2, a.down_time2)
						ELSE
							NULL
					END
			END) AS work_status2,

			-- 第三班次
			a.sequence3 AS sequence3,
			a.up_time3 AS up_time3,
			a.down_time3 AS down_time3,
			a.time_state3 AS time_state3,
			a.leave_time3 AS leave_time3,
			a.travel_time3 AS travel_time3,
			a.work_time3 AS work_time3,
			-- 实际上班打卡时间
			bu3.matching_time AS real_up_time3,
			-- 迟到时间，≤0 没有迟到 >0 有迟到
			`fun_get_up_down_time`(a.up_time3, bu3.matching_time) AS up_late_time3,

			-- 实际下班打卡时间
			bd3.matching_time AS real_down_time3,
			-- 早退时间，>0 有早退 ≤0 没有早退
			`fun_get_up_down_time`(bd3.matching_time, a.down_time3) AS down_early_time3,
			-- 打卡状态，0：正常，1：迟到，2：早退，3：迟到早退，4：上班缺卡，5：下班缺卡，6：旷工
			(CASE a.attendance_type
				WHEN 1 THEN
					NULL
				ELSE
					CASE ISNULL(a.sequence3)
						WHEN 0 THEN
							`fun_get_work_status`(bu3.matching_time, bd3.matching_time, a.up_time3, a.down_time3)
						ELSE
							NULL
					END
			END) AS work_status3

FROM attendance_summary a
		LEFT JOIN
		 (SELECT * FROM attendance_data_matching) bu1
			ON bu1.account_id = a.account_id
			 AND bu1.organize_id = a.organize_id
			 AND bu1.matching_date = a.datetime
			 AND bu1.attendance_sequence = a.sequence1
			 AND bu1.up_down_mark = 0
		LEFT JOIN
		 (SELECT * FROM attendance_data_matching) bd1
			ON bd1.account_id = a.account_id
			 AND bd1.organize_id = a.organize_id
			 AND bd1.matching_date = a.datetime
			 AND bd1.attendance_sequence = a.sequence1
			 AND bd1.up_down_mark = 1
		LEFT JOIN
		 (SELECT * FROM attendance_data_matching) bu2
			ON bu2.account_id = a.account_id
			 AND bu2.organize_id = a.organize_id
			 AND bu2.matching_date = a.datetime
			 AND bu2.attendance_sequence = a.sequence2
			 AND bu2.up_down_mark = 0
		LEFT JOIN
		 (SELECT * FROM attendance_data_matching) bd2
			ON bd2.account_id = a.account_id
			 AND bd2.organize_id = a.organize_id
			 AND bd2.matching_date = a.datetime
			 AND bd2.attendance_sequence = a.sequence2
			 AND bd2.up_down_mark = 1
		LEFT JOIN
		 (SELECT * FROM attendance_data_matching) bu3
			ON bu3.account_id = a.account_id
			 AND bu3.organize_id = a.organize_id
			 AND bu3.matching_date = a.datetime
			 AND bu3.attendance_sequence = a.sequence3
			 AND bu3.up_down_mark = 0
		LEFT JOIN
		 (SELECT * FROM attendance_data_matching) bd3
			ON bd3.account_id = a.account_id
			 AND bd3.organize_id = a.organize_id
			 AND bd3.matching_date = a.datetime
			 AND bd3.attendance_sequence = a.sequence3
			 AND bd3.up_down_mark = 1;


CREATE OR REPLACE VIEW view_attendance_summary_matching_final AS
SELECT a.*,
	fun_get_day_status(a.work_status1, a.work_status2, a.work_status3) AS day_status

FROM view_attendance_summary_matching a;
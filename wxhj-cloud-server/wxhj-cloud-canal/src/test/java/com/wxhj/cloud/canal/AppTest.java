package com.wxhj.cloud.canal;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wxhj.cloud.canal.config.CanalServerConfig;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class AppTest {
	@Resource
	DataSource dataSource;
	@Resource
	CanalServerConfig canalServerConfig;

	// @Test
	public void test1() {

		Connection connection;
		try {
			connection = dataSource.getConnection();
			Statement stmt = connection.createStatement();

			ResultSet resultSet = stmt.executeQuery("select * from platformDB.t_test1");
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();

			while (resultSet.next()) {
				for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
					System.out.println(resultSetMetaData.getColumnName(i) + ":" + resultSet.getObject(i).toString());
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
		}

	}

	@Test
	public void test2() {
		System.out.println(canalServerConfig.getTableName());
	}

}

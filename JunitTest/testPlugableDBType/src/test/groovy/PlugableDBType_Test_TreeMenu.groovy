import static org.junit.Assert.*;

import org.junit.Test;


class PlugableDBType_Test_TreeMenu {
	
	/**
	 * Check normal use with job's directory
	 */
	@Test
	void test01(){
		def data2CreateMenu = GenerateTreeMenu.getData2CreateMenu("var/job")
		String result = GenerateTreeMenu.getMenuItemsStr(data2CreateMenu['root'], data2CreateMenu['output'])
		def expectedMySQL_1 = '<li>Database_Area<ul><li><a>MySQL.Database_Area.InnoDBTablespace_Free.job</a></li></ul></li>'
		def expectedMySQL_2 = '<li>Database_Structure<ul><li><a>MySQL.Database_Structure.Parameter.job</a></li></ul></li>'
		def expectedMySQL_3 = '<li>Performance<ul><li><a>MySQL.Performance.InnoDBBufferPool.job</a></li></ul></li>'
		def expectedMySQL_4 = '<li>Proactive_Check<ul><li><a>MySQL.Proactive_Check.Aborted_Information.job</a></li></ul></li>'
		def expectedOS_1 = '<li>OS<ul><li><a>OS.CPU_Linux.job</a></li></ul></li>'
		def expectedPG_1 = '<li>Database_Area<ul><li><a>Postgres.Database_Area.Tablespace_Free.job</a></li></ul></li>'
		def expectedPG_2 = '<li>Database_Statistic<ul><li><a>Postgres.Database_Statistic.Database_Info.job</a></li></ul></li>'
		def expectedPG_3 = '<li>Database_Structure<ul><li><a>Postgres.Database_Structure.Database_Version.job</a></li></ul></li>'
		def expectedPG_4 = '<li>Performance<ul><li><a>Postgres.Performance.Buffer_Cache_Hit.job</a></li></ul></li>'
		def expectedPG_5 = '<li>Proactive_Check<ul><li><a>Postgres.Proactive_Check.Resource_Limit.job</a></li></ul></li>'
		def expectedMSSQL_1 = '<li>Database_Area<ul><li><a>SQL_Server.Database_Area.Database_free.job</a></li></ul></li>'
		def expectedMSSQL_2 = '<li>Database_Structure<ul><li><a>SQL_Server.Database_Structure.Database_Version.job</a></li></ul></li>'
		def expectedMSSQL_3 = '<li>Performance<ul><li><a>SQL_Server.Performance.Buffer_Cache_Hit_Ratio.job</a></li></ul></li>'
		def expectedMSSQL_4 = '<li>Proactive_Check<ul><li><a>SQL_Server.Proactive_Check.Batch_Requests.job</a></li></ul></li>'
		assertTrue(result.contains(expectedMySQL_1)) 
		assertTrue(result.contains(expectedMySQL_2))
		assertTrue(result.contains(expectedMySQL_3))
		assertTrue(result.contains(expectedMySQL_4))
		assertTrue(result.contains(expectedOS_1))
		assertTrue(result.contains(expectedPG_1))
		assertTrue(result.contains(expectedPG_2))
		assertTrue(result.contains(expectedPG_3))
		assertTrue(result.contains(expectedPG_4))
		assertTrue(result.contains(expectedPG_5))
		assertTrue(result.contains(expectedMSSQL_1))
		assertTrue(result.contains(expectedMSSQL_2))
		assertTrue(result.contains(expectedMSSQL_3))
		assertTrue(result.contains(expectedMSSQL_4))
	}
	
	/**
	 * Check normal use with list job name
	 * Note that list job name has no .job extension because it should be gotten from mongodb
	 */
	@Test
	void test02(){
		def listJob = [
				"MySQL.Database_Area.InnoDBTablespace_Free",
				"MySQL.Performance.InnoDBBufferPool",
				"Postgres.Database_Area.Tablespace_Free",
				"SQL_Server.Database_Area.Database_free",
				"SQL_Server.Performance.Buffer_Cache_Hit_Ratio",
				"SQL_Server.Proactive_Check.Batch_Requests"
			]
		
		def data2CreateMenu = GenerateTreeMenu.getData2CreateMenu(listJob)
		String result = GenerateTreeMenu.getMenuItemsStr(data2CreateMenu['root'], data2CreateMenu['output'])
		def expectedMySQL_1 = '<li>Database_Area<ul><li><a>MySQL.Database_Area.InnoDBTablespace_Free</a></li></ul></li>'
		def expectedMySQL_3 = '<li>Performance<ul><li><a>MySQL.Performance.InnoDBBufferPool</a></li></ul></li>'
		def expectedPG_1 = '<li>Database_Area<ul><li><a>Postgres.Database_Area.Tablespace_Free</a></li></ul></li>'
		def expectedMSSQL_1 = '<li>Database_Area<ul><li><a>SQL_Server.Database_Area.Database_free</a></li></ul></li>'
		def expectedMSSQL_3 = '<li>Performance<ul><li><a>SQL_Server.Performance.Buffer_Cache_Hit_Ratio</a></li></ul></li>'
		def expectedMSSQL_4 = '<li>Proactive_Check<ul><li><a>SQL_Server.Proactive_Check.Batch_Requests</a></li></ul></li>'
		assertTrue(result.contains(expectedMySQL_1))
		assertTrue(result.contains(expectedMySQL_3))
		assertTrue(result.contains(expectedPG_1))
		assertTrue(result.contains(expectedMSSQL_1))
		assertTrue(result.contains(expectedMSSQL_3))
		assertTrue(result.contains(expectedMSSQL_4))
	}
}

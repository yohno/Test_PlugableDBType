#!/bin/bash
if [ ! $# -eq 1 ];then
   echo "Incorrect parameter !"
   echo "Usage: ./testJobDocServlet.sh /wiperdog_home_path"
   exit
else
	wiperdog_home=$1
	#check if wiperdog directory exists
	if [ ! -d "$wiperdog_home" ];then
		echo " >> Wiperdog directory does not exists : $wiperdog_home"
		exit
	fi

	# Config dbms information
	echo "WRITING DBMS INFORMATION INTO: " $wiperdog_home/etc/use_for_xwiki.cfg
	cat > $wiperdog_home/etc/use_for_xwiki.cfg <<eof
		[
			"DbType": [
				"MySQL": "@MYSQL",
				"SQL_Server": "@MSSQL",
				"Postgres": "@PGSQL",
				"MongoDB": "@MONGO",
				"MariaDB": "@MARIA"
			],
			"TreeMenuInfo": [
				"MySQL": [
					"Database_Area":[],
					"Database_Statistic":[],
					"Database_Structure":[],
					"FaultManagement":[],
					"Performance":[],
					"Proactive_Check":[],
					"Others":[]
				],
				"SQL_Server":[
					"Database_Area":[],
					"Database_Statistic":[],
					"Database_Structure":[],
					"FaultManagement":[],
					"Performance":[],
					"Proactive_Check":[],
					"Others":[]
				],
				"Postgres":[
					"Database_Area":[],
					"Database_Statistic":[],
					"Database_Structure":[],
					"FaultManagement":[],
					"Performance":[],
					"Proactive_Check":[],
					"Others":[]
				],
				"MongoDB":[
					"Database_Area":[],
					"Database_Statistic":[],
					"Database_Structure":[],
					"FaultManagement":[],
					"Performance":[],
					"Proactive_Check":[],
					"Others":[]
				],
				"OS":[],
				"Others":[]
			]
		]
eof

	# Restart wiperdog to apply new configuration of dbms
	echo "** STARTING WIPERDOG ..."
	fuser -k 13111/tcp
	/bin/sh $wiperdog_home/bin/startWiperdog.sh > /dev/null 2>&1 &
	sleep 30
	echo "** WIPERDOG WAS RUNNING ..."
fi

echo ">>>>> TEST GET METHOD OF JobDocServlet <<<<<"
content=$(curl -i -H "Accept: application/json" -H "Content-Type: application/json" 'http://localhost:13111/JobDocInfoServlet')
echo "Result response data after GET request:"
echo "--------------------------------------------"
echo $content
echo "--------------------------------------------"
if [[ $content =~ .*'<li>MongoDB'.* ]]
then
	echo "Get data to generate menu successfully!!!"
else
	echo "Get data to generate menu failure!!!"
fi
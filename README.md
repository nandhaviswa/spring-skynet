# spring-skynet

### setup

- Navigate to your workspace directory and clone the repo
```sh
git clone https://github.com/nandhaviswa/spring-skynet.git
cd ~/Workspace/spring-skynet
```

- Ensure docker is running in your system and execute the below commands
- Import the schema and seeders data from [src/main/resources/schema.sql](https://github.com/nandhaviswa/spring-skynet/blob/main/src/main/resources/schema.sql)
```sh
docker pull mysql:8.0
docker run -d --name jdbc-mysql -e MYSQL_ROOT_PASSWORD=root mysql:8.0
docker exec -it jdbc-mysql /bin/sh

mysql -uroot -proot
mysql -u'profilemanager' -p'profilemanager' -h'127.0.0.1'
```

- Execute the below commands to run all the tests and launch the application
```sh
JDBC_DATABASE_HOST='172.17.0.2' JDBC_DATABASE_DATABASE=profilemanager JDBC_DATABASE_USERNAME=profilemanager JDBC_DATABASE_PASSWORD=profilemanager mvn clean test
JDBC_DATABASE_HOST='172.17.0.2' JDBC_DATABASE_DATABASE=profilemanager JDBC_DATABASE_USERNAME=profilemanager JDBC_DATABASE_PASSWORD=profilemanager mvn clean spring-boot:run
```

### teardown
- Stop and remove all the containers
```sh
docker container ls
docker stop jdbc-mysql
docker rm jdbc-mysql
```

### schema

    Profile{
        number id,
        string name,
        ProfileDetail[] profileDetail,
    }

    ProfileDetail{
        string otherDetails
    }

### api

#### request
	GET /getProfileDetails?ProfileId=1 HTTP/1.1

#### response

	HTTP/1.1 200 OK
	Content-Type: application/json

	[{
		"id": 1,
		"name": "nandha",
		"profileDetails": [{
			"otherDetails": "nandha detail 1"
		}, {
			"otherDetails": "nandha detail 2"
		}]
	}, {
		"id": 2,
		"name": "kumar",
		"profileDetails": []
	}, {
		"id": 3,
		"name": "viswa",
		"profileDetails": [{
			"otherDetails": "viswa detail 1"
		}]
	}]

### sample URLs

- [http://127.0.0.1:8080/getProfileDetails?ProfileId=1](http://127.0.0.1:8080/getProfileDetails?ProfileId=1){:target="_blank"}
- [http://127.0.0.1:8080/getProfileDetails?ProfileId=2](http://127.0.0.1:8080/getProfileDetails?ProfileId=2){:target="_blank"}
- [http://127.0.0.1:8080/getProfileDetails?ProfileId=3](http://127.0.0.1:8080/getProfileDetails?ProfileId=3){:target="_blank"}
- [http://127.0.0.1:8080/getProfileDetails?ProfileId=7](http://127.0.0.1:8080/getProfileDetails?ProfileId=7){:target="_blank"}
- [http://127.0.0.1:8080/getProfileDetails](http://127.0.0.1:8080/getProfileDetails){:target="_blank"}
- [http://127.0.0.1:8080/getProfileDetails?ProfileId=](http://127.0.0.1:8080/getProfileDetails?ProfileId=){:target="_blank"}
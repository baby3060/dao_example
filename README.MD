# DAO 테스트

## DAO 계속해서 발전해 나갈 생각 

### RDBMS는 MariaDB 사용 => Oracle 등의 다른 DBMS도 사용할 예정

#### 모든 DBMS에서 사용할 데이터베이스 이름은 simple

##### Gradle에서 OJDBC 연결하려고 하니 안떴을 때 해결 방법

1. oracle home에서 ojdbc7.jar 다운

2. mvn install:install-file -Dfile={ojdbc7.jar 저장 위치} -DgroupId=com.oracle -DartifactId=ojdbc7 -Dversion=12.1.0 -Dpackaging=jar

3. build.gradle
<pre>
    repositories {
        ...
        # Maven Local 저장소에서 검색
        mavenLocal()
    }
</pre>

4. build.gradle
<pre>
    dependencies {
        ...
        # groupId, artifactId, version 확인 잘 할 것
        compile('com.oracle:ojdbc7:12.1.0')
    }
</pre>
package org.devops

//scan
def SonarScan(sonarServer,projectName,projectDesc,projectPath){

        //定义服务器列表
    def servers = ["test":"sonarqube-server","prod":"sonarqube-prod"]

    withSonarQubeEnv("${servers[sonarServer]}"){
        def scannerHome = tool "sonarscanner"
        def sonarDate = sh returnStdout: true, script: 'date  +%Y%m%d%H%M%S'
        sonarDate = sonarDate - "\n"
        sh """ 
        ${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${projectName} \
        -Dsonar.projectName=${projectName} -Dsonar.projectVersion=${sonarDate} -Dsonar.ws.timeout=30 \
        -Dsonar.projectDescription=${projectDesc} -Dsonar.links.homepage=http://www.baidu.com \
        -Dsonar.sources=${projectPath} -Dsonar.sourceEncoding=UTF-8 -Dsonar.java.binaries=target/classes \
        -Dsonar.java.test.binaries=target/test-classes -Dsonar.java.surefire.report=target/surefire-reports
        """
    }
}

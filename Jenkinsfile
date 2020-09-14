pipeline {
    agent any
    stages {
        stage ('Build Tasks Api') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployTasksApi -Dsonar.host.url=http://localhost:9000 -Dsonar.login=0efbef98ab4afa1f21d66454d14fed37d3010c14 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/RootController.java,**/TasksApiApplication.java,**/ValidacaoHandler.java,**/WebConfiguration.java,**/model/Task.java,**validation/Error.java"
                }
            }
        }
        stage ('Deploy Api') {
            steps {
				deploy adapters: [tomcat8(credentialsId: 'tomcat_login', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-api', war: 'target/tasks-api.war'
            }
        }		
        stage ('Build Tasks Api Tests') {
            steps {
				dir('tasks-api-test'){
					git credentialsId: 'github_login', url: 'https://github.com/digamo/tasks-api-test'
					bat 'mvn test'				
				}
            }
        }		
		
		
		
		
    }
}



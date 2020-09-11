pipeline {
    agent any
    stages {
        stage ('Build Tasks API') {
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
			environment{
				scannerHome = tool 'SONAR_SCANNER'
			}
            steps {
				withSonarQubeEnv('SONAR_LOCAL'){
					bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployTasksApi -Dsonar.host.url=http://localhost:9000 -Dsonar.login=0efbef98ab4afa1f21d66454d14fed37d3010c14 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/RootController.java,**/TasksApiApplication.java,**/ValidacaoHandler.java,**/WebConfiguration.java,**/model/Task.java"				
				}
            }
        }
		stage ('Quality Gate'){
			steps{
				timeout(time: 1, unit: 'MINUTES'){
					waitForQualityGate abortPipeline: true
				}
			}
		}
		
	}
}



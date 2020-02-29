pipeline {
   agent any
   
   environment {
       PROJECT_NAME="JSOAGGER Core"
       EMAIL_SENDER="jenkins@nexitia.com"
       DEV_MAILING_LIST="rmvonji@gmail.com"
   }
   
   options {
        timestamps()
    }
   
   stages {
        stage ("Initialize") {
			steps {
				sh '''
					echo "PATH = ${PATH}"
					echo "M2_HOME = ${M2_HOME}"
					echo "JAVA_HOME = ${JAVA_HOME}"					
				'''
				
                git credentialsId: 'jenkins_github_credentials' ,
                    url: 'https://github.com/jsoagger/jsoagger-core.git',
                    branch: 'master'
			}
		}
       
        stage('Build Snapshot') {
            steps {
                sh "mvn -Dmaven.test.failure.ignore=true -DskipTests=true install"
            }
        }
        
        stage('Test Snapshot') {
            steps {
                echo "Running unit tests and integration tests"
                sh "mvn -Dmaven.test.failure.ignore=false -DskipTests=false verify"
            }
        }
        
        stage('Deploy Snapshot') {
		    steps {
                sh '''
				    echo "Deploy to nexus"
				    echo "Deloying snapshot is not very usefull"
				    echo "You can be just share artifact or do a local build instead"

				    #mvn clean deploy --settings .maven.xml -DskipTests=true -Dmaven.javadoc.skip=true -B -U -Prelease
			    '''
            } 
            post {  
                 success {  
                    emailext   body: "$PROJECT_NAME, build success.<br/> You can check jenkins console output at $BUILD_URL to view full the results.", 
                            subject: "$PROJECT_NAME, build Success", 
                            from: "$EMAIL_SENDER", 
                            to: "$DEV_MAILING_LIST", 
                            attachLog: false;
                }  
             	failure {
                	emailext    body: "$PROJECT_NAME, build failed. <br/> You can check jenkins console output at $BUILD_URL to view full the results.", 
                            subject: "$PROJECT_NAME - build Failure", 
                            from: "$EMAIL_SENDER", 
                            to: "$DEV_MAILING_LIST", 
                            attachLog: true;
             	}  
        	}
      	}
    
      	stage('Perform release?') {
        	steps {
        		timeout(time: 600, unit: 'SECONDS'){
        			script {
	                    def perfomRelease = input(
 							id: 'perfomRelease', message: 'Do you want to perform version release?', ok:'Release this build' 
						)
                	}	
        		}
        	}
      	}
      
      	stage('Release') {
         	steps {
	         	withCredentials([usernamePassword(credentialsId: 'jenkins_github_credentials', passwordVariable: 'GIT_PASSWORD', usernameVariable: 'GIT_USERNAME')])
	         	{
	                sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -ENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -DdryRun=true -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" release:prepare -B -V -Prelease'
			        sh 'mvn --settings .maven.xml -DENV_GIT_USERNAME=$GIT_USERNAME -ENV_GIT_PASSWORD=$GIT_PASSWORD -Dresume=false -Dmaven.test.failure.ignore=true -DskipTests=true -Darguments=\"-Dmaven.javadoc.skip=true\" -B -V release:prepare release:perform -Prelease'
	         	}
         	}
         	
         	post {  
         		 always {
         		 	sh '''
         		 		echo "End of job"
		         	'''
         		 }
				 success {  
					 emailext   body: "$PROJECT_NAME, NEW RELEASE is avalaible.<br/> You can check jenkins console output at $BUILD_URL to view full the results.", 
								subject: "$PROJECT_NAME, new Release avalaible", 
								from: "$EMAIL_SENDER", 
								to: "$DEV_MAILING_LIST", 
								attachLog: false;
				 }  
				 failure {
					emailext    body: "$PROJECT_NAME, RELEASE failed. <br/> You can check jenkins console output at $BUILD_URL to view full the results.", 
								subject: "$PROJECT_NAME, RELEASE Failure", 
								from: "$EMAIL_SENDER", 
								to: "$DEV_MAILING_LIST", 
								attachLog: true;
				}  
			}
      	}
    }
}

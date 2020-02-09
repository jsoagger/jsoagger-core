pipeline {
   agent any

   stages {
       stage ("initialize") {
			steps {
				sh '''
					echo "PATH = ${PATH}"
					echo "M2_HOME = ${M2_HOME}"
					echo "JAVA_HOME = ${JAVA_HOME}"
					'''
				}
		}
       
      stage('Build') {
         steps {
            // Get some code from a GitHub repository
            git 'https://github.com/jsoagger/jsoagger-core.git'

            // Run Maven on a Unix agent.
            sh "mvn -Dmaven.test.failure.ignore=true clean install"

         }
      }
      
      stage('Versions') {
		 steps {
                    script {
                        // Show the select input modal
                        def releaseInput = input(
 							id: 'releaseInput', message: 'Release version', parameters: [
 								[$class: 'StringParameterDefinition', defaultValue: '', name: 'release']
							]
						)
						def nextVersionInput = input(
 							id: 'nextVersionInput', message: 'Next version', parameters: [
 								[$class: 'StringParameterDefinition', defaultValue: '', name: 'release']
							]
						)
                    }
            }      
      }
      
      stage('Release') {
         steps {
			sh '''
				echo "Provide versions"
			'''
         }
      }
   }
}
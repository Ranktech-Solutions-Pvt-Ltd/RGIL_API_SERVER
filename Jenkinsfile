pipeline {
    agent any

    
    stages {
        stage("Build and create jar") {
            tools {
                maven 'local_maven'
            }
            steps {
                echo 'building the application'
                sh 'mvn clean package -DSkipTests'
            }

            post{
                failure{
                    echo "sending mail"
                }
            }
        }

        

        

        // PRODUCTION STAGES
        stage("Build docker image") { 
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                script {
                    app = docker.build("asia.gcr.io/rgil-live/api")
                }
            }
            
        }
        
        stage("Push to GCR") {
            when {
                expression {
                    BRANCH_NAME == 'main'
                }
            }
            steps {
                
                script {
                    version = sh(returnStdout: true, script: 'date +%d-%m-%y').trim()
                    println("app version ${version}")
                    docker.withRegistry('https://asia.gcr.io', 'gcr:rgil-live') {
                        app.push("${version}_${BUILD_NUMBER}")
                        app.push("latest")
                    }
                }
            }
            
        }



        
    }
    post{
        always{
            echo "sending mail"
            
        }
        success {
            echo "pipeline completed successfully"
            emailext attachLog: false, to: "sarbajitb@ranktechsolutions.com, chayanc@ranktechsolutions.com", body: "${JOB_NAME} pipeline with build number ${BUILD_NUMBER} is successful.", subject: "${JOB_NAME}(${BUILD_NUMBER}) - Pipeline Successful" 
            
            
        }
        failure {
            echo 'pipeline failed'
            emailext attachLog: true, to: "sarbajitb@ranktechsolutions.com, chayanc@ranktechsolutions.com", body: "${JOB_NAME} pipeline with build number ${BUILD_NUMBER} is failed. Log has been attached", subject: "${JOB_NAME}(${BUILD_NUMBER}) - Pipeline Failed"
            
            
        }
        
    }
}

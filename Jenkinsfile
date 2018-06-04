pipeline {
    // Launch on agent with specific label
    // This agent is provisoned by
    // https://plugins.jenkins.io/docker-plugin
    agent { 
        node { 
            label 'android-agent'
        } 
    }
    // Standard way to launch docker images in pipeline.
    // Launches images on master node and requires master to have
    // Docker binary installed.
    // When master node itself is deployed using docker image this approach is
    // not ideal because it requires to add Docker binary to Jenkins image.
    /*
    agent {
        docker {
            image 'alapshin/android-build-env' 
        }
    }
    */

    stages {
        stage('build') {
            steps {
                sh './decrypt.sh'
                sh './build.sh'
            }
        }
        stage('deploy') {
            steps {
                sh './publish.sh'
            }
        }
    }

    post { 
        success {
            archiveArtifacts artifacts: 'app/build/**/*.apk', fingerprint: true 
        }
    }
}

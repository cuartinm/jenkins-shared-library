#!/user/bin/env groovy

def call(Map stageParams) {
    stage('SonarQube') {
        withSonarQubeEnv('SonarQube Server') {
            def scannerHome = tool 'sonar-scanner'
            sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=${stageParams.projectKey} -Dsonar.sources=${stageParams.src}"
        }
        timeout(time: 5, unit: 'MINUTES') {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
                error "Pipeline aborted due to quality gate failure: ${qg.status}"
            }
        }
    }
}



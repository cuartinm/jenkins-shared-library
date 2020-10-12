#!/user/bin/env groovy

def call(Map params) {
  stage('deploy artifact') {
    sh(
      script: """
        curl -u ${params.username}:${params.password} \
        -X PUT -k "${params.artifactory_url}/${params.repository}/${params.application}/${params.version}/${params.artifact}" \
        -T ${params.artifact}
      """
    )
  }
}



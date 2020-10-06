#!/user/bin/env groovy

def call(Map params) {
  stage('git checkout') {
    checkout([
      $class: 'GitSCM',
      branches: [[name: "${params.branch}"]],
      doGenerateSubmoduleConfigurations: false,
      extensions: [[
        $class: 'CloneOption',
        noTags: false,
        reference: '',
        shallow: false
      ]],
      submoduleCfg: [],
      userRemoteConfigs: [[
        url: "${params.repository}"
      ]]
    ])
  }
}



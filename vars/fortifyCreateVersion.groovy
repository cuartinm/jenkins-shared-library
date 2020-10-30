#!/user/bin/env groovy

def call(Map params) {
  def code;
 
  //Crear version
  def newVersionBuilder = new groovy.json.JsonBuilder();
  newVersionBuilder {
    name 'soluciondeoficinas'
    description 'Resultados de escaneo de SCA desde Jenkins'
    active true
    commited false
    project(
      id: 7,
      issueTemplateId: 'Prioritized-HighRisk-Project-Template',
    )
    issueTemplateId 'Prioritized-HighRisk-Project-Template'
  };
  
  def httpConn = new URL("https://isistin.bancoavvillas.net/ssc/api/v1/projectVersions").openConnection();
  httpConn.setRequestMethod("POST");
  httpConn.setRequestProperty("Authorization", "FortifyToken NDEyYzEyYjgtNzQ5ZS00NzViLWIyYTUtZThjNmExZGQ4NDZm")
  httpConn.setRequestProperty("Accept", "application/json");
  httpConn.setDoOutput(true);
  httpConn.getOutputStream().write(newVersionBuilder.toString().getBytes("UTF-8"));
  code = httpConn.getResponseCode();
  httpConn.disconnect();

  

  //Commit Version
  def commitBuilder = new groovy.json.JsonBuilder();
  commitBuilder {
    committed true
  };
  
  httpConn = new URL("https://isistin.bancoavvillas.net/ssc/api/v1/projectVersions/${idVersion}").openConnection();
  httpConn.setRequestMethod("PUT");
  httpConn.setRequestProperty("Authorization", "FortifyToken NDEyYzEyYjgtNzQ5ZS00NzViLWIyYTUtZThjNmExZGQ4NDZm")
  httpConn.setRequestProperty("Accept", "application/json");
  httpConn.setDoOutput(true);
  httpConn.getOutputStream().write(commitBuilder.toString().getBytes("UTF-8"));
  code = httpConn.getResponseCode();
  httpConn.disconnect();

}

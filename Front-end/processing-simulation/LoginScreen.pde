

class LoginScreen implements Screen {
  String host = "http://tamedogisnotdog.com/snowtools/external/RestServer/v1";
  ControlP5 cp;
  PApplet app;
  controlP5.Textfield name;
  controlP5.Textfield password;
  controlP5.Bang login;
  
  LoginScreen(ControlP5 cp5, PApplet papp) {
    cp = cp5;
    app = papp;
    name = cp5.addTextfield("name")
            .setPosition(100, 125)
            .setSize(200, 30);
            
    password = cp5.addTextfield("password")
            .setPosition(100, 180)
            .setSize(200, 30);
            
    login = cp5.addBang("login")
            .setPosition(155,250)
            .setSize(80,40)
            .setId(100);
    login.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER);
  }
  
  public void display() {
  
  }
  
  public void hide() {
    name.hide();
    password.hide();
    login.hide();
  }
  
  public void login() {
    try {
      String userEndpoint = host + "/login";
      String cardEndpoint;
      org.json.JSONObject json = new org.json.JSONObject();
      json.put("username", cp.get(Textfield.class,"name").getText());
      json.put("pwd", cp.get(Textfield.class,"password").getText());
      String response = ((starbucks)app).sendPostReqeust(json, userEndpoint);
      org.json.JSONObject user = new org.json.JSONObject(response);
      ((starbucks)app).setUser(new UserInfo(user.getInt("userId"), 
          user.getString("username"), user.getString("userToken")));
      //println(((starbucks)app).getUser().userToken);
      cardEndpoint = host + "/getCurrentUseCard/" + ((starbucks)app).getUser().userId + "/" + ((starbucks)app).getUser().userToken;
      response = ((starbucks)app).sendGetRequest(cardEndpoint); //<>//
      org.json.JSONObject card = new org.json.JSONObject(response);
      ((starbucks)app).setCard(new CardInfo(card.getInt("cardId"), 
          card.getString("cardNum"), card.getString("cardCode"), card.getInt("cardHolderId"), card.getDouble("balance")));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

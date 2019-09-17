class AddCardScreen implements Screen {
  String host = "http://tamedogisnotdog.com/snowtools/external/RestServer/v1";
  ControlP5 cp;
  PApplet app;
  controlP5.Textfield cardNumber;
  controlP5.Textfield cardCode;
  controlP5.Bang submit;
  
  public AddCardScreen(ControlP5 cp5, PApplet papp) {
    cp = cp5;
    app = papp;
    cardNumber = cp5.addTextfield("Card Number")
            .setPosition(100, 125)
            .setSize(200, 30);
            
     cardCode = cp5.addTextfield("Card Code")
            .setPosition(100, 180)
            .setSize(200, 30);
            
    submit = cp5.addBang("submit")
            .setPosition(155,250)
            .setSize(80,40)
            .setId(9);
            
    submit.getCaptionLabel().align(ControlP5.CENTER, ControlP5.CENTER);
    
    hideAll();
  }
  
  public void display() {
    showAll();
  }
  
  public void hide() {
    hideAll();
  }
  
  private void hideAll() {
    cardNumber.clear();
    cardNumber.hide();
    cardCode.clear();
    cardCode.hide();
    submit.hide();
  }
  
  private void showAll() {
    cardNumber.show();
    cardCode.show();
    submit.show();
  }
  
  public void addCard() {
    
  }
}

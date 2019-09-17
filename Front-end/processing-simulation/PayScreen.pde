class PayScreen implements Screen {
  PApplet app;
  ControlP5 cp;
  controlP5.Textlabel numberLabel, number, codeLabel, code;
  controlP5.Button scan;
  public PayScreen(ControlP5 cp5, PApplet app) {
    this.app = app;
    this.cp = cp5;
    numberLabel = cp5.addTextlabel("numberLabel")
                    .setText("Number: ")
                    .setPosition(80,150)
                    .setColorValue(0xffffff00)
                    .setFont(createFont("Georgia",20));
                    
    number = cp5.addTextlabel("number")
                .setText("")
                .setPosition(200,150)
                .setColorValue(0xffffff00)
                .setFont(createFont("Georgia",20));
                
    codeLabel = cp5.addTextlabel("codeLabel")
                   .setText("Code: ")
                   .setPosition(80,200)
                   .setColorValue(0xffffff00)
                   .setFont(createFont("Georgia",20));
    
    code = cp5.addTextlabel("code")
                .setText("")
                .setPosition(200,200)
                .setColorValue(0xffffff00)
                .setFont(createFont("Georgia",20));
                    
    scan = cp5.addButton("scan")
           .setValue(10)
           .setPosition(150, 300)
           .updateSize()
           .setId(7);
           
    hideAll();
  }  
  
  public void display() {
     showAll();
  }
  
  public void hide() {
     hideAll();
  }
  
  private void hideAll() {
    numberLabel.hide();
    number.hide();
    codeLabel.hide();
    code.hide();
    scan.hide();
  }
  
  private void showAll() {
    numberLabel.show();
    number.setText(((starbucks)app).getCard().cardNum);
    number.show();
    codeLabel.show();
    code.setText(((starbucks)app).getCard().cardCode);
    code.show();
    scan.show();
  }
  
  public void makeAPayment() {
    println("make a payment");
  }
}

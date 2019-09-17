

class CardsScreen implements Screen {
  PApplet app;
  ControlP5 cp;
  controlP5.Textlabel text;
  controlP5.Button pay;
  public CardsScreen(ControlP5 cp5, PApplet app) {
    this.app = app;
    this.cp = cp5;
    text = cp5.addTextlabel("label")
              .setText("$0.0")
              .setPosition(150,200)
              .setColorValue(0xffffff00)
              .setFont(createFont("Georgia",20));
                    
    pay = cp5.addButton("pay")
           .setValue(10)
           .setPosition(150, 250)
           .updateSize()
           .setId(6);
    hideAll();
  }  
  
  public void display() {
     showAll();
  }
  
  public void hide() {
     hideAll();
  }
  
  private void hideAll() {
      text.hide();
      pay.hide();
  }
  
  private void showAll() {
      text.setText("$" + ((starbucks)app).getCard().cardBalance);
      text.show();
      pay.show();
  }
}

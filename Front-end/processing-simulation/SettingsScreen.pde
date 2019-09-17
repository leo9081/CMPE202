class SettingsScreen implements Screen {
  ControlP5 cp;
  controlP5.Button add;
  public SettingsScreen(ControlP5 cp5) {
    this.cp = cp5;
    add = cp5.addButton("Add Card")
           .setPosition(150, 250)
           .updateSize()
           .setId(8);
    hide();
  }
  
  public void display() {
    add.show();
  }
  
  public void hide() {
    add.hide();
  }
}

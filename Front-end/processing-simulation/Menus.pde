

class Menu {
  controlP5.Button cardsButton, paymentsButton, storeButton, rewardsButton, settingsButton;
  public Menu (ControlP5 cp5) {
    
    int startPoint = 27;
    int buttonWidth = 70;
    int verticalPoint = 500;
    
    cardsButton = cp5.addButton("cards")
       .setPosition(startPoint,verticalPoint)
       .updateSize()
       .setId(1);
       
    paymentsButton = cp5.addButton("payments")
       .setPosition(startPoint + buttonWidth,verticalPoint)
       .updateSize()
       .setId(2);
       
    storeButton = cp5.addButton("store")
       .setPosition(startPoint + 2 * buttonWidth,verticalPoint)
       .updateSize()
       .setId(3);
       
    rewardsButton = cp5.addButton("rewards")
       .setPosition(startPoint + 3 * buttonWidth,verticalPoint)
       .updateSize()
       .setId(4);
       
    settingsButton = cp5.addButton("settings")
       .setPosition(startPoint + 4 * buttonWidth,verticalPoint)
       .updateSize()
       .setId(5);
       
       hideAll();
  }
    
    
    private void hideAll() {
      cardsButton.hide();
      paymentsButton.hide();
      storeButton.hide();
      rewardsButton.hide();
      settingsButton.hide();
    }
    
    public void display() {
      cardsButton.show();
      paymentsButton.show();
      storeButton.show();
      rewardsButton.show();
      settingsButton.show();
    }
}

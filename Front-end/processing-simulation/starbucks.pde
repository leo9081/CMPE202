import controlP5.*;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import org.json.*;

ControlP5 cp5;


Screen current, login, cards, pay, payments, settings, addCard;
Menu menu;

UserInfo user;
CardInfo card;

void setup() {
  size(400,600);
  noStroke();
  cp5 = new ControlP5(this);
  background(160);
  login = new LoginScreen(cp5, this);
  menu = new Menu(cp5);
  cards = new CardsScreen(cp5, this);
  pay = new PayScreen(cp5, this);
  payments = new PaymentsScreen(cp5);
  settings = new SettingsScreen(cp5);
  addCard = new AddCardScreen(cp5, this);
  current = login;
}

void draw() {
  background(0);
  current.display();
}

public void controlEvent(ControlEvent theEvent) {
  println(theEvent.getController().getName());
  switch(theEvent.getId()) {
      // cards menu
    case(1):
      current.hide();
      current = cards;
      break;
      // payments menu
    case(2):
      current.hide();
      current = payments;
      break;
      // settings menu
    case(5):
      current.hide();
      current = settings;
      break;
      // pay button on cards screen
    case(6):
      current.hide();
      current = pay;
      break;
      // scan button on pay screen
    case(7):
      ((PayScreen)pay).makeAPayment();
      current.hide();
      current = cards;
      break;
      // add card button on settings screen
    case(8):
      current.hide();
      current = addCard;
      break;
      // submit button on add card screen
    case(9):
      ((AddCardScreen)addCard).addCard();
      current.hide();
      current = cards;
    case(100):
      ((LoginScreen)login).login();
      menu.display();
      current.hide();
      current = cards;
    default:
      break;
  }
}

public void pay(int theValue) {
    println("a button event from pay button: " + theValue);
  }
  
public void setUser(UserInfo user) {
  this.user = user;
}
  
public UserInfo getUser() {
  return this.user;
}

public void setCard(CardInfo card) {
  this.card = card;
}

public CardInfo getCard() {
  return this.card;
}

public String sendGetRequest(String endpoint) {
  String content = new String();
  try{
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpGet get = new HttpGet(endpoint);
    HttpResponse response = httpClient.execute(get);
    content = EntityUtils.toString(response.getEntity());
  } catch (Exception e) {
    e.printStackTrace();
  }
  return content;
}

public String sendPostReqeust(org.json.JSONObject body, String endpoint) {
  
  String content = new String();
  try{
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    HttpPost post = new HttpPost(endpoint);
    post.setEntity(new StringEntity(body.toString()));
    post.setHeader("Content-type", "application/json");
    HttpResponse response = httpClient.execute(post);
    content = EntityUtils.toString(response.getEntity());
  } catch (Exception e) {
    e.printStackTrace();
  }
  return content;
}

package edu.ntnu.stud;

/**
 * This is the main class for the train dispatch application.
 * It is responsible for launching the user interface.
 *
 * @author Adrian Aleksander Buczek
 * @version v1.0.0-release
 * @since v0.3.0-alpha
 */
public class App {
  /**
   * Main method that runs the application.
   *
   * @param args are the arguments for the main-method.
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.run();
  }


}

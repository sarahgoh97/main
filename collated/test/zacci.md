# zacci
###### \java\seedu\address\logic\LogicManagerTest.java
``` java
    public LogicManagerTest() {
        model.login("maxSecurityLevelUser", 999);
    }
```
###### \java\seedu\address\TestApp.java
``` java
    public Model getModel() {
        Model copy = new ModelManager((model.getAddressBook()), new UserPrefs());
        model.login("maxSecurityLevelUser", 999);
        ModelHelper.setFilteredList(copy, model.getFilteredPersonList());
        return copy;
    }
    //@author

    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Creates an XML file at the {@code filePath} with the {@code data}.
     */
    private <T> void createDataFileWithData(T data, String filePath) {
        try {
            File saveFileForTesting = new File(filePath);
            FileUtil.createIfMissing(saveFileForTesting);
            XmlUtil.saveDataToFile(saveFileForTesting, data);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
```

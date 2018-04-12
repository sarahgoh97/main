# zacci
###### \java\seedu\address\logic\commands\AddCommandTest.java
``` java
        @Override
        public void login(String username, int securityLevel){};

        @Override
        public void logout(){};

        @Override
        public Session getSession() {
            return new Session();
        };

        @Override
        public String getSessionDetails() {
            return "";
        }

        @Override
        public boolean attemptLogin(String username, String password) {
            return true;
        }

        @Override
        public int getSecurityLevel() {
            return 5;
        }

        @Override
        public void addUser(User user) {};

        @Override
        public void deleteUser(String user) {};

        @Override
        public boolean checkIsLoggedIn() {
            return true;
        };
```
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

    //@author sarahgoh97
    public Model getNewModel() {
        Model copy = new ModelManager((model.getAddressBook()), new UserPrefs());
        model.login("maxSecurityLevelUser", 999);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        ModelHelper.setFilteredList(copy, model.getFilteredPersonList());
        return copy;
    }
```

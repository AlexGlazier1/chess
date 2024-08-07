package dataaccess;

import model.GameData;
import model.UserData;
import server.Server;

import java.util.HashMap;
import java.util.Map;

public class MemoryUserDAO implements UserDAO {

        public Map<String, UserData> memoryUserMap = new HashMap<>();



        public void createUser(UserData user){
                memoryUserMap.put(user.username(), user);
        }

        public boolean readUser(String username){

                if(memoryUserMap.containsKey(username)){
                        return true;
                }
                return false;
        }

        public void updateUser(UserData user){
                memoryUserMap.put(user.username(), user);
        }

        public void deleteUser(UserData user){
                memoryUserMap.remove(user.username());
        }

        public Map<String, UserData> getMemoryUserMap(){
                return memoryUserMap;
        }

        public void clearAllUsers(){
                memoryUserMap.clear();
        }


}

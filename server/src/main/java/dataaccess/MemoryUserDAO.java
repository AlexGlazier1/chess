package dataaccess;

import model.UserData;

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

        // Uncomment this when if you need to: public void updateUser(UserData user){
        // Uncomment this when if you need to:         memoryUserMap.put(user.username(), user);
        // Uncomment this when if you need to: }

        // Uncomment this when if you need to: public void deleteUser(UserData user){
        // Uncomment this when if you need to:         memoryUserMap.remove(user.username());
        // Uncomment this when if you need to: }

        public Map<String, UserData> getMemoryUserMap(){
                return memoryUserMap;
        }

        public void clearAllUsers(){
                memoryUserMap.clear();
        }


}

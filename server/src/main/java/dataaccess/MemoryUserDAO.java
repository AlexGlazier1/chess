package dataaccess;

import model.UserData;
import server.Server;

public class MemoryUserDAO implements UserDAO {


        public void createUser(UserData user){
                Server.memoryUserMap.put(user.username(), user);
        }

        public boolean readUser(String username){

                if(Server.memoryUserMap.containsKey(username)){
                        return true;
                }
                return false;
        }

        public void updateUser(UserData user){
                Server.memoryUserMap.put(user.username(), user);
        }

        public void deleteUser(UserData user){
                Server.memoryUserMap.remove(user.username());
        }

        public void clearAllUsers(){
                Server.memoryUserMap.clear();
        }


}

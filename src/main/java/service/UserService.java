package service;

import domain.User;
import dto.user.UserResponse;
import exception.DataAccessException;
import exception.IncompleteUserException;
import exception.UserNotFoundException;
import repository.dao.FileUserDao;
import repository.dao.UserDao;
import java.util.Iterator;

import java.util.List;

public class UserService {
  private final UserDao userDao;

  public UserService(UserDao userDao) {
    this.userDao = userDao;
  }

  public UserResponse getByUsername(String username)
      throws UserNotFoundException, DataAccessException {
    User user =
        userDao.findByUsername(username).orElseThrow(() -> new UserNotFoundException(username));
    return new UserResponse(user.getId(), user.getUsername());
  }

  // Ejercicio 6
  public User getValidatedUser(String username, UserDao userDao)
      throws UserNotFoundException, IncompleteUserException, DataAccessException {
    throw new UnsupportedOperationException("Ejercicio 6: implement getValidatedUser");
  }

  // Ejercicio 9
  public List<User> getUsersPresentInFileAndDao(FileUserDao fileUserDao, UserDao userDao) throws DataAccessException {    
    List<User> listaUsuario = fileUserDao.findAll(); //propaga la excepcion
    Iterator<User> userIterator = listaUsuario.iterator();
    User useri = null; 
        while (userIterator.hasNext()) {
          useri = userIterator.next(); 
         try {
             if ((userDao.findByUsername(useri.getUsername())).isEmpty()) {
            userIterator.remove(); 
          }
         } finally {
         }//ignora si hubo una excepcion y continua

        }
        return (listaUsuario);
  }
}

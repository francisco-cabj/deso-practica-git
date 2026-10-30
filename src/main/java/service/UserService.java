package service;
import domain.User;
import dto.user.UserResponse;
import exception.DataAccessException;
import exception.IncompleteUserException;
import exception.UserNotFoundException;
import repository.dao.FileUserDao;
import repository.dao.UserDao;

import java.util.Collections;
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

  // Ejercicio 9 (Parte B)
public List<User> getUsersPresentInFileAndDao(FileUserDao fileUserDao, UserDao userDao) {
      // Return an empty list if no users exist in fileUserDao
      List<User> listaUsuario = fileUserDao.findAll();
      if (listaUsuario == null || listaUsuario.isEmpty()) {
          return Collections.emptyList();
      }
      
      Iterator<User> userIterator = listaUsuario.iterator();
      while (userIterator.hasNext()) {
          User useri = userIterator.next(); 
          try {
              // Remove the user if they are not present in userDao (empty result)
              if (userDao.findByUsername(useri.getUsername()).isEmpty()) {
                  userIterator.remove(); 
              }
          } catch (DataAccessException ignore) {
              // Ignore/remove the user if userDao throws a DataAccessException for that user
              userIterator.remove();
          }
      }
      return (listaUsuario);
  }
}

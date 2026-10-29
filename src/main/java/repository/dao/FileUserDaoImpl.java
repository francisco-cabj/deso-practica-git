package repository.dao;

import domain.User;
import exception.DataAccessException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FileUserDaoImpl implements FileUserDao {
  private final File file;

  public FileUserDaoImpl() {
    this.file = new File("users.txt");
  }

  // visible for testing, so tests can point at a temp file instead of the real one
  FileUserDaoImpl(File file) {
    this.file = file;
  }

  // Ejercicio 9 (Parte A)
  @Override
public List<User> findAll() throws DataAccessException {
    try (BufferedReader reader = new BufferedReader(new FileReader(this.file))) {
      String userLine = reader.readLine();
      UUID userid = null;
      String username = null;
      ArrayList<User> UserList = new ArrayList<User>() ;
      while (userLine != null) {//haya contenido para leer
       String[] data = userLine.split(" "); //data[0] = UUID , data[1] == Nombre , split separa el string en 2, ver user.txt
      if (data.length > 1) {
          userid = UUID.fromString(data[0]);
          username =  data[1];
          UserList.add(User.builder().id(userid).username(username).build());
        }
        userLine = reader.readLine(); //salta a la siguiente linea, el i++
      }

      return (UserList);
    } catch (IOException e) {
      throw new DataAccessException("Error al leer usuario", e);
    }
  }
}

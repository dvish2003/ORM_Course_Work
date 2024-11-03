package lk.ijse.BO.Impl;

import lk.ijse.BO.UserBO;
import lk.ijse.DAO.DAOFactory;
import lk.ijse.DAO.UserDAO;
import lk.ijse.DTO.UserDTO;
import lk.ijse.Entity.User;

public class UserBOImpl implements UserBO {
  UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DaoType.User);
    @Override
    public boolean save(UserDTO dto) throws Exception {
        return userDAO.save(new User(dto.getUser_id(),dto.getUsername(),dto.getAddress(),dto.getUser_phone(),dto.getUser_email(),dto.getPosition(),dto.getPassword()));
    }

    @Override
    public boolean update(UserDTO dto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(UserDTO dto) throws Exception {
        return false;
    }
}

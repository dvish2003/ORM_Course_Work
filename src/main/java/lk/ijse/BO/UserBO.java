package lk.ijse.BO;

import lk.ijse.DTO.UserDTO;

public interface UserBO extends SuperBO {
    public boolean save(UserDTO dto) throws Exception;

    public boolean update(UserDTO dto) throws Exception;

    public boolean delete(UserDTO dto)throws Exception;
}

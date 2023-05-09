package com.kgisl.OnlineVotingSystemUsingSpringMvc.Dao;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.kgisl.OnlineVotingSystemUsingSpringMvc.Model.Voter;

public class VoterDAO {

    private JdbcTemplate jdbcTemplate;
    
    public VoterDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Voter> listAllVoters() {
        String sql = "SELECT * FROM voters";
        RowMapper<Voter> rowMapper = new VoterRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    public boolean insertVoter(Voter voter) {
        String sql = "INSERT INTO voters (voter_id, name, email, password, age, gender, ward) VALUES (?,?,?,?,?,?,?)";
        return jdbcTemplate.update(sql, voter.getVoter_id(), voter.getName(), voter.getEmail(), voter.getPassword(),
                voter.getAge(), voter.getGender(), voter.getWard()) > 0;
    }

    public boolean updateVoter(Voter voter) {
        String sql = "UPDATE voters SET name = ?, email = ?, password = ?, age = ?, gender = ?, ward = ? WHERE voter_id = ?";
        return jdbcTemplate.update(sql, voter.getName(), voter.getEmail(), voter.getPassword(), voter.getAge(),
                voter.getGender(), voter.getWard(), voter.getVoter_id()) > 0;
    }

    public boolean deleteVoter(Voter voter) {
        String sql = "DELETE FROM voters where voter_id = ?";
        return jdbcTemplate.update(sql, voter.getVoter_id()) > 0;
    }

    public List<Voter> getEmailPass() {
        String sql = "SELECT email,password FROM voters";
        RowMapper<Voter> rowMapper = new VoterRowMapper();
        return jdbcTemplate.query(sql, rowMapper);
    }

    private static class VoterRowMapper implements RowMapper<Voter> {
        @Override
        public Voter mapRow(java.sql.ResultSet resultSet, int rowNum) throws java.sql.SQLException {
            String voter_id = resultSet.getString("voter_id");
            String name = resultSet.getString("name");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");
            int age = resultSet.getInt("age");
            String gender = resultSet.getString("gender");
            String ward = resultSet.getString("ward");
            return new Voter(voter_id, name, email, password, age, gender, ward);
        }
    }
}


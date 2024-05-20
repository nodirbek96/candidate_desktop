package candidate.entity.candidate;

import java.util.List;

public interface CandidateCallbacks {
    void createCandidateTable();
    CandidateDto insertCandidate(CandidateDto candidate);
    CandidateDto updateCandidate(CandidateDto candidate);
    CandidateDto getCandidateById(Integer id);
    List<CandidateDto> getAllCandidates();
    boolean deleteCandidateById(Integer id);
    List<CandidateDto> searchByFirstFirstnameOrLastnameOrMiddlename(String suffix);
    List<CandidateDto> searchByPhoneOrPassport(String suffix);
    List<CandidateDto> searchByJobPlaceOrOccupationOrEducationOrPosition(String suffix);
    List<CandidateDto> searchByBirthDate(String suffix);
}
package forjun.web.module.history.api.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
public class HistoryCreateRequest {

    private String category;

    private String project;

    private String subject;

    private String description;

    private List<String> historySkill = new ArrayList<>();

    private LocalDate historyStartDate;

    private LocalDate  historyEndDate;
}

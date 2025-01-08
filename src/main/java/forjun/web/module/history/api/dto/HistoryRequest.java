package forjun.web.module.history.api.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class HistoryRequest {

    private int historyId;

    private String category;

    private String project;

    private String subject;

    private String description;

    private List<String> HistorySkill = new ArrayList<>();

    private LocalDateTime historyStartDate;

    private LocalDateTime historyEndDate;
}

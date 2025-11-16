package forjun.web.module.history.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class HistoryResponse {

    private Integer id;

    private String category;

    private String project;

    private String subject;

    private String description;

    private List<String> historySkill;

    private LocalDate historyStartDate;

    private LocalDate historyEndDate;
}

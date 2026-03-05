package io.github.ilkinnnnn.taskmanager.model;

import io.github.ilkinnnnn.taskmanager.model.enums.AttachmentFileType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@NoArgsConstructor
@Entity
public class Attachment {
    @Id
    @GeneratedValue
    private Long id;

    @Setter
    @Column(nullable = false)
    private String fileName;

    @Setter
    @Column(nullable = false, length = 500)
    private String fileUrl;

    @Setter
    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private AttachmentFileType fileType;

    @Setter
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "task_id", nullable = false)
    private Task task;
}

package com.bookstore.jpa.dtos;

import java.util.Set;
import java.util.UUID;

public record BookRecordDTO(
  String title,
  UUID publisherId,
  Set<UUID> authorId,
  String reviewComment
) { }

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;

import org.junit.Rule;
import org.junit.Test;

import io.github.faketime.FakeTimeMixin;
import io.github.faketime.FakeTimeRule;

public class AbsoluteTimeTest implements FakeTimeMixin {

  @Rule
  public FakeTimeRule rule = new FakeTimeRule();

  @Test
  public void stopTimeAt_epochMillisecond() {
    stopTimeAt(0);
    assertThat(System.currentTimeMillis()).isEqualTo(0);

    stopTimeAt(20);
    assertThat(System.currentTimeMillis()).isEqualTo(20);
  }

  @Test
  public void stopTimeAt_Instant() {
    stopTimeAt(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneOffset.UTC).toInstant());

    assertThat(ZonedDateTime.now(ZoneOffset.UTC))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneOffset.UTC));
  }

  @Test
  public void stopTimeAt_ZonedDateTime() {
    stopTimeAt(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneOffset.UTC));

    assertThat(ZonedDateTime.now(ZoneOffset.UTC))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneOffset.UTC));
  }

  @Test
  public void stopTimeAt_OffsetDateTime() {
    stopTimeAt(OffsetDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneOffset.UTC));

    assertThat(OffsetDateTime.now(ZoneOffset.UTC))
        .isEqualTo(OffsetDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneOffset.UTC));
  }

  @Test
  public void stopTimeAt_LocalDateTime() {
    stopTimeAt(LocalDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000));

    assertThat(LocalDateTime.now())
        .isEqualTo(LocalDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000));
  }

  @Test
  public void stopTimeAt_7() {
    stopTimeAt(2000, 10, 9, 8, 7, 6, 5);

    assertThat(LocalDateTime.now())
        .isEqualTo(LocalDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000));
  }

  @Test
  public void stopTimeAt_6() {
    stopTimeAt(2000, 10, 9, 8, 7, 6);

    assertThat(ZonedDateTime.now())
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 0, ZoneId.systemDefault()));
  }

  @Test
  public void stopTimeAt_5() {
    stopTimeAt(2000, 10, 9, 8, 7);

    assertThat(OffsetDateTime.now())
        .isEqualTo(OffsetDateTime.of(2000, 10, 9, 8, 7, 0, 0, ZoneId.systemDefault().getRules().getOffset(LocalDateTime.now())));
  }

  @Test
  public void stopTimeAt_4() {
    stopTimeAt(2000, 10, 9, 8);

    assertThat(LocalDateTime.now())
        .isEqualTo(LocalDateTime.of(2000, 10, 9, 8, 0, 0, 0));
  }

  @Test
  public void stopTimeAt_3() {
    stopTimeAt(2000, 10, 9);

    assertThat(LocalDateTime.now())
        .isEqualTo(LocalDateTime.of(2000, 10, 9, 0, 0, 0, 0));
    assertThat(LocalDate.now())
        .isEqualTo(LocalDate.of(2000, 10, 9));
  }


  @Test
  public void stopTimeAtUtc_7() {
    stopTimeAtUtc(2000, 10, 9, 8, 7, 6, 5);

    assertThat(ZonedDateTime.now(ZoneOffset.UTC))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneOffset.UTC));
  }

  @Test
  public void stopTimeAtUtc_6() {
    stopTimeAtUtc(2000, 10, 9, 8, 7, 6);

    assertThat(LocalDateTime.now(ZoneOffset.UTC))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 0, ZoneOffset.UTC).toLocalDateTime());
  }

  @Test
  public void stopTimeAtUtc_5() {
    stopTimeAtUtc(2000, 10, 9, 8, 7);

    assertThat(OffsetDateTime.now(ZoneOffset.UTC))
        .isEqualTo(OffsetDateTime.of(2000, 10, 9, 8, 7, 0, 0, ZoneOffset.UTC));
  }

  @Test
  public void stopTimeAtUtc_4() {
    stopTimeAtUtc(2000, 10, 9, 8);

    assertThat(ZonedDateTime.now(ZoneOffset.UTC))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 0, 0, 0, ZoneOffset.UTC));
  }

  @Test
  public void stopTimeAtUtc_3() {
    stopTimeAtUtc(2000, 10, 9);

    assertThat(ZonedDateTime.now(ZoneOffset.UTC))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 0, 0, 0, 0, ZoneOffset.UTC));
  }


  @Test
  public void stopTimeAt_withZone_7() {
    stopTimeAt(2000, 10, 9, 8, 7, 6, 5, ZoneId.of("Europe/Tallinn"));

    assertThat(ZonedDateTime.now(ZoneId.of("Europe/Tallinn")))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 5_000_000, ZoneId.of("Europe/Tallinn")));
  }

  @Test
  public void stopTimeAt_withZone_6() {
    stopTimeAt(2000, 10, 9, 8, 7, 6, ZoneId.of("Europe/Tallinn"));

    assertThat(LocalDateTime.now(ZoneId.of("Europe/Tallinn")))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 7, 6, 0, ZoneId.of("Europe/Tallinn")).toLocalDateTime());
  }

  @Test
  public void stopTimeAt_withZone_5() {
    stopTimeAt(2000, 10, 9, 8, 7, ZoneId.of("Europe/Tallinn"));

    assertThat(OffsetDateTime.now(ZoneId.of("Europe/Tallinn")))
        .isEqualTo(OffsetDateTime.of(2000, 10, 9, 8, 7, 0, 0, ZoneId.of("Europe/Tallinn").getRules().getOffset(LocalDateTime.now())));
  }

  @Test
  public void stopTimeAt_withZone_4() {
    stopTimeAt(2000, 10, 9, 8, ZoneId.of("Europe/Tallinn"));

    assertThat(ZonedDateTime.now(ZoneId.of("Europe/Tallinn")))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 8, 0, 0, 0, ZoneId.of("Europe/Tallinn")));
  }

  @Test
  public void stopTimeAt_withZone_3() {
    stopTimeAt(2000, 10, 9, ZoneId.of("Europe/Tallinn"));

    assertThat(ZonedDateTime.now(ZoneId.of("Europe/Tallinn")))
        .isEqualTo(ZonedDateTime.of(2000, 10, 9, 0, 0, 0, 0, ZoneId.of("Europe/Tallinn")));
  }
}

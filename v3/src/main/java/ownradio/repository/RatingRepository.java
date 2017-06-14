package ownradio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ownradio.domain.Rating;
import ownradio.domain.Track;
import ownradio.domain.User;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * Интерфейс репозитория, для хранения рейтингов прослушанных треков
 *
 * @author Alpenov Tanat
 */
public interface RatingRepository extends JpaRepository<Rating, UUID> {

    Rating findByUser(User user);

    Rating findByUserAndTrack(User user, Track track);

    @Query(value = "SELECT\n" +
            "         CAST(userid AS VARCHAR) AS userid,\n" +
            "         CAST(trackid AS VARCHAR) AS trackid,\n" +
            "         ratingsum\n" +
            "       FROM ratings\n" +
            "       WHERE userid = ?1\n" +
            "       UNION ALL\n" +
            "       SELECT\n" +
            "         CAST(r1.userid AS VARCHAR) AS userid,\n" +
            "         CAST(r1.trackid AS VARCHAR) AS userid,\n" +
            "         r1.ratingsum\n" +
            "       FROM ratings r1\n" +
            "       WHERE r1.trackid IN (SELECT\n" +
            "                              r3.trackid\n" +
            "                            FROM ratings r3\n" +
            "                            WHERE r3.userid = ?1)\n" +
            "       AND r1.userid <> ?1\n" +
            "       AND exists(SELECT 1\n" +
            "                  FROM users u\n" +
            "                  WHERE u.recid = r1.userid AND u.experience >= 10)", nativeQuery = true)
    List<Object[]> getRatingTracks(UUID userId);
}

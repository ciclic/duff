package com.gustavo.spotifyplaylist;

import com.wrapper.spotify.SpotifyApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpotifyplaylistApplication {

	public static void main(String[] args) {
		//SpringApplication.run(SpotifyplaylistApplication.class, args);

		GetAPlayList.getPlaylist_Sync();
	}
}

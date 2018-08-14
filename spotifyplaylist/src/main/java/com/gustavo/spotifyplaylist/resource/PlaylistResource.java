package com.gustavo.spotifyplaylist.resource;

import com.gustavo.spotifyplaylist.GetAPlayList;
import com.gustavo.spotifyplaylist.MyPlaylist;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.regex.Matcher;

@RestController
@RequestMapping("/playlist")
public class PlaylistResource {


    private Matcher playlist;

    @GetMapping("/playlist/userid/{userId}/playlistid/{playlistId}")
    public MyPlaylist getPlaylist(@PathVariable String userId, @PathVariable String playlistId){
        return GetAPlayList.getPlaylist_Sync(userId, playlistId);
    }



}

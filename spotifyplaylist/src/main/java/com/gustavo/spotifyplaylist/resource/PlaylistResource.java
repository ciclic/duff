package com.gustavo.spotifyplaylist.resource;

import com.gustavo.spotifyplaylist.model.MyPlaylist;
import com.gustavo.spotifyplaylist.service.GetAPlayList;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/playlist")
public class PlaylistResource {

    @GetMapping("/playlist/userid/{userId}/playlistid/{playlistId}")
    public MyPlaylist getPlaylist(@PathVariable String userId, @PathVariable String playlistId){
        return GetAPlayList.getPlaylist_Sync(userId, playlistId);
    }



}

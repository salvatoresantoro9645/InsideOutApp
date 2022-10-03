package com.example.insideout;

import android.net.Uri;

import androidx.annotation.OptIn;
import androidx.media3.common.MediaItem;
import androidx.media3.common.util.UnstableApi;
import androidx.media3.datasource.RawResourceDataSource;

import java.util.ArrayList;
import java.util.List;

public class MoodPlaylistCreator {

    private String mood;

    public MoodPlaylistCreator(String mood){
        this.mood = mood;
    }


    //return a list of MediaItem to use inside MusicActivity's ExoPlayer
    public List<MediaItem> createPlaylist(){
        List<MediaItem> mediaItems = null;
        switch(this.mood){
            case "JOY":
                mediaItems = getJoyPlaylist();
                break;
            case "SORROW":
                mediaItems = getSorrowPlaylist();
                break;
            case "ANGER":
                mediaItems = getAngerPlaylist();
                break;
            case "DISGUST":
                mediaItems = getDisgustPlaylist();
                break;
            case "FEAR":
                mediaItems = getFearPlaylist();
                break;
            case "HUNGRY":
                mediaItems = getHungryPlaylist();
                break;
        }
        return mediaItems;
    }

    //return a list of MusicCard to use inside MusicActivity's RecycleView
    //and MusicCardAdapter's MusicCardHolder
    public List<MusicCard> createCardList(List<MusicCard> cardItems){
        switch(this.mood){
            case "JOY":
                cardItems = getJoyCardList(cardItems);
                break;
            case "SORROW":
                cardItems = getSorrowCardList(cardItems);
                break;
            case "ANGER":
                cardItems = getAngerCardList(cardItems);
                break;
            case "DISGUST":
                cardItems = getDisgustCardList(cardItems);
                break;
            case "FEAR":
                cardItems = getFearCardList(cardItems);
                break;
            case "HUNGRY":
                cardItems = getHungryCardList(cardItems);
                break;
        }
        return cardItems;
    }

    @OptIn(markerClass = UnstableApi.class)
    public List<MediaItem> getJoyPlaylist(){
        MediaItem item = null;
        Uri uri = null;
        List<MediaItem> mediaItems = new ArrayList<MediaItem>();

        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_felicita_puttana_the_giornalisti.mp3?alt=media&token=e4cb28e3-9f16-4a49-933c-78454378f6ca");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_sofia_alvaro_soler.mp3?alt=media&token=98fa54d8-a7bc-4384-bbcb-d134028c9108");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_dancing_queen_abba.mp3?alt=media&token=226cbe11-3d99-4d4a-9962-a7c75b115225");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_happy_pharrell_williams.mp3?alt=media&token=869b36d5-824e-4c72-9f45-0cb2332c007a");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_on_top_of_the_world_imagine_dragons.mp3?alt=media&token=d4c4dde5-a200-4c3b-83a2-902739021917");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_walking_on_sunshine_katrina_and_the_waves.mp3?alt=media&token=c537ab90-ad57-4cbc-bd92-362dddd0ed51");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_can_t_stop_the_feeling_justin_timberlake.mp3?alt=media&token=8e76d8e8-90ad-4fce-8f5a-5cf836fe4e44");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_mambo_salentino_alessandra_amoroso.mp3?alt=media&token=215e88b2-727f-466c-a821-54ea98298dd0");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_dynamite_bts.mp3?alt=media&token=973e8768-7a00-42e8-99d9-2024ae633579");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/joy_iko_iko_justin_wellington_feat_small_jam.mp3?alt=media&token=d8b51853-8305-426b-9fe6-76b5af46bba9");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);

        return mediaItems;

        //In case of resources in raw directory (too much data in apk memory)
        /*
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_felicita_puttana_the_giornalisti);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_sofia_alvaro_soler);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_dancing_queen_abba);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_happy_pharrell_williams);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_on_top_of_the_world_imagine_dragons);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_walking_on_sunshine_katrina_and_the_waves);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_can_t_stop_the_feeling_justin_timberlake);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_mambo_salentino_alessandra_amoroso);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_dynamite_bts);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.joy_iko_iko_justin_wellington_feat_small_jam);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        */
    }

    public List<MusicCard> getJoyCardList(List<MusicCard> cardItems){
        MusicCard card = null;

        card = new MusicCard(R.mipmap.image_music_green, "Felicità puttana", "The Giornalisti");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Sofia", "Alvaro Soler");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Dancing queen", "ABBA");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Happy", "Pharrell Williams");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "On top of the world", "Imagine Dragons");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Walking on Sushine", "Katrina & The Waves");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Can't stop the feeling", "Justing Timberlake");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Mambo salentino", "Alessandra Amoroso feat. Boomdabash");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Dynamite", "BTS");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Iko Iko", "Justin Wellington & Small Jam");
        cardItems.add(card);

        return cardItems;
    }



    @OptIn(markerClass = UnstableApi.class)
    public List<MediaItem> getSorrowPlaylist(){
        MediaItem item = null;
        Uri uri = null;
        List<MediaItem> mediaItems = new ArrayList<MediaItem>();

        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_don_t_worry_be_happy_bobby_mcferrin.mp3?alt=media&token=303ed9e1-37fc-4d2f-815d-44eab1f52c77");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_another_love_tom_odell.mp3?alt=media&token=4247f492-8a9d-4cfd-ac22-b680b90ff01b");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_caruso_lucio_dalla.mp3?alt=media&token=4a3d8fbf-a91a-4cca-a9c7-2ef232cc1a68");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_non_e_mai_un_errore_raf.mp3?alt=media&token=d820736d-6d56-4385-9b23-05255d23f9bb");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_river_flows_in_you_yiruma.mp3?alt=media&token=2b511b9a-cfb5-4e6c-ab04-79ce8d3b2ddb");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_trouble_coldplay.mp3?alt=media&token=be6edeb4-aca0-4cc8-8530-605d3a9eb35b");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_changes_xxxtentacion.mp3?alt=media&token=801e8c3e-9b50-4199-91cf-905b2e6b0174");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_easy_on_me_adele.mp3?alt=media&token=e1e3cff5-4956-4224-8c3d-a55673db551a");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_stay_rihanna_feat_mikky_ekko.mp3?alt=media&token=7802bf66-ced3-435b-87f3-ab0f0f4764eb");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/sorrow_how_to_save_a_life_the_fray.mp3?alt=media&token=5a183579-0bef-4730-92df-e290141f16f8");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);

        return mediaItems;

        //In case of resources in raw directory (too much data in apk memory)
        /*
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_don_t_worry_be_happy_bobby_mcferrin);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_another_love_tom_odell);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_caruso_lucio_dalla);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_non_e_mai_un_errore_raf);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_river_flows_in_you_yiruma);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_trouble_coldplay);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_changes_xxxtentacion);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_easy_on_me_adele);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_stay_rihanna_feat_mikky_ekko);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.sorrow_how_to_save_a_life_the_fray);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
         */
    }

    public List<MusicCard> getSorrowCardList(List<MusicCard> cardItems){
        MusicCard card = null;

        card = new MusicCard(R.mipmap.image_music_green, "Don't worry be happy", "Bobby McFerrin");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Another love", "Tom Odell");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Caruso", "Lucio Dalla");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Non è mai un errore", "Raf");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "River flows in you", "Yiruma");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Trouble", "Coldplay");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Changes", "XXXTentacion");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Easy on me", "Adele");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Stay", "Rihanna feat. Mikky Ekko");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "How to save a life", "The Fray");
        cardItems.add(card);

        return cardItems;
    }



    @OptIn(markerClass = UnstableApi.class)
    public List<MediaItem> getAngerPlaylist(){
        MediaItem item = null;
        Uri uri = null;
        List<MediaItem> mediaItems = new ArrayList<MediaItem>();

        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_relax_take_it_easy_mika.mp3?alt=media&token=f9bcff08-9cc4-4558-8d8c-dae951183bbc");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_creep_radiohead.mp3?alt=media&token=3f86c0bc-0ab1-48d5-91b1-b468810ca244");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_believer_imagine_dragon.mp3?alt=media&token=dc16fc8d-e62a-40a6-b212-bd3d6a95e180");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_body_parts_i_denti_marracash.mp3?alt=media&token=1cb0718c-2ab6-4dcc-a88d-af92ba525984");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_lunedi_salmo.mp3?alt=media&token=12c228d2-4fd8-4610-80a3-ae5a96e4ed3f");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_autostima_psicologi.mp3?alt=media&token=aa9812d6-7523-47b8-a3f2-51478234b160");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_poison_mostro.mp3?alt=media&token=b19fa84e-5d46-40a1-a5c7-905cee844e3d");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_territorial_pissing_nirvana.mp3?alt=media&token=caea85cb-20ea-4c89-a696-298c0d5fcc3f");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_you_can_look_bruce_springsteen.mp3?alt=media&token=1bc7d722-91fd-4c55-b9b1-9ccd3a5311ad");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_rosso_di_rabbia_anastasio.mp3?alt=media&token=32d7037c-bd10-4f10-b31a-2d471b70eb78");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_vent_anni_maneskin.mp3?alt=media&token=c43de5ae-5c28-490f-9119-65de1290ea00");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_don_t_look_back_in_anger_oasis.mp3?alt=media&token=d4484a03-f992-4786-b0bf-a82bfe2fed8c");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/anger_vaffanculo_marco_masini.mp3?alt=media&token=8337055f-85b5-43d8-a646-d0dcbc93db29");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);

        return mediaItems;

        //In case of resources in raw directory (too much data in apk memory)
        /*
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_relax_take_it_easy_mika);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_creep_radiohead);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_believer_imagine_dragon);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_body_parts_i_denti_marracash);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_lunedi_salmo);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_autostima_psicologi);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_poison_mostro);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_territorial_pissing_nirvana);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_you_can_look_bruce_springsteen);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_rosso_di_rabbia_anastasio);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_vent_anni_maneskin);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_don_t_look_back_in_anger_oasis);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.anger_vaffanculo_marco_masini);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
         */
    }

    public List<MusicCard> getAngerCardList(List<MusicCard> cardItems){
        MusicCard card = null;

        card = new MusicCard(R.mipmap.image_music_green, "Relax take it easy", "Mika");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Creep", "Radiohead");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Believer", "Imagine Dragons");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "BODY PARTS I denti", "Marracash");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Lunedì", "Salmo");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Autostima", "Psicologi");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Poison", "Mostro");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Territorial pissing", "Nirvana");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "you can look (but better no touch)", "Bruce SpringSteen");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Rosso di rabbia", "Anastasio");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Vent'anni", "Maneskin");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Don't look back in anger", "Oasis");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Vaffanculo", "Marco Masini");
        cardItems.add(card);

        return cardItems;
    }



    @OptIn(markerClass = UnstableApi.class)
    public List<MediaItem> getDisgustPlaylist(){
        MediaItem item = null;
        Uri uri = null;
        List<MediaItem> mediaItems = new ArrayList<MediaItem>();

        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_disgusto_totale_punkreas.mp3?alt=media&token=ae5fe928-c116-4c7d-9beb-6d451d59cfd5");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_faccio_schifo_pala.mp3?alt=media&token=b35d94f6-9039-4063-98e8-28030ec99954");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_mancarsi_comacose.mp3?alt=media&token=818bfdc2-1238-4470-be78-34b6f2546f59");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_gaetano_calcutta.mp3?alt=media&token=8d14ee29-2848-4675-83fa-16642875e7ed");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_carnival_of_disgust_falconer.mp3?alt=media&token=85358951-5c21-4849-a260-e7ad98224041");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_ventitre_merlot.mp3?alt=media&token=6b827191-4c4b-4969-94e8-58ac82af6c31");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_villipendio_willie_peyote.mp3?alt=media&token=eb699ddc-4069-491d-aaba-d8e26542360d");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_cirano_francesco_guccini.mp3?alt=media&token=f10f3c53-acb0-4821-bc0e-d907a21f5d57");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_dentro_la_scatola_mondo_marcio.mp3?alt=media&token=4cf33a7b-daf2-4dc1-9b29-dacab64ef991");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/disgust_disgusting_ernia.mp3?alt=media&token=d604c87c-d00d-4b5d-962c-b21af261c0aa");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);

        return mediaItems;

        //In case of resources in raw directory (too much data in apk memory)
        /*
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_disgusto_totale_punkreas);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_faccio_schifo_pala);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_mancarsi_comacose);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_gaetano_calcutta);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_carnival_of_disgust_falconer);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_ventitre_merlot);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_villipendio_willie_peyote);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_cirano_francesco_guccini);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_dentro_la_scatola_mondo_marcio);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.disgust_disgusting_ernia);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
         */
    }

    public List<MusicCard> getDisgustCardList(List<MusicCard> cardItems){
        MusicCard card = null;

        card = new MusicCard(R.mipmap.image_music_green, "Disgusto totale", "Punkreas");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Faccio schifo", "Pala");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Mancarsi", "Comacose");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Gaetano", "Calcutta");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Carnival of disgust", "Falconer");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Ventitre", "M.E.R.L.O.T");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Villipendio", "Willie Peyote");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Cirano", "Francesco Guccini");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Dentro la scatola", "Mondo Marcio");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Disgusting", "Ernia");
        cardItems.add(card);

        return cardItems;
    }



    @OptIn(markerClass = UnstableApi.class)
    public List<MediaItem> getFearPlaylist(){
        MediaItem item = null;
        Uri uri = null;
        List<MediaItem> mediaItems = new ArrayList<MediaItem>();

        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_non_avere_paura_tommaso_paradiso.mp3?alt=media&token=cd9ccfa8-a59a-4c80-9d06-a0f93bb0ec7b");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_cherofobia_martina_attili.mp3?alt=media&token=6b4eba04-d808-4689-951c-107f9c502630");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_it_s_my_life_bon_jovi.mp3?alt=media&token=88a14782-e29b-4dca-ad72-f9c58001913f");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_fear_for_nobody_maneskin.mp3?alt=media&token=d0df7cab-69db-49c0-9b87-eea1d26035a1");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_niente_paura_ligabue.mp3?alt=media&token=d9fa634e-7671-4f5c-9cb4-bc989ffbcf00");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_six_forty_seven_instupendo.mp3?alt=media&token=cf5f75ed-74ff-4389-8c3f-05954e1d45c2");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_the_ghost_niviro.mp3?alt=media&token=34c23464-30fc-4b39-91e2-43805a3c9a69");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_day_of_chaos_kevin_macleod.mp3?alt=media&token=9eba8910-defa-42cb-a59b-0ddec859f3d6");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_cradles_sub_urban.mp3?alt=media&token=77594f0c-d15c-47e7-9a93-64b4a522ec58");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/fear_main_titles_charlie_clouser.mp3?alt=media&token=9f2850ab-61ea-4fe2-8fe3-f14b05f69ba5");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);

        return mediaItems;

        //In case of resources in raw directory (too much data in apk memory)
        /*
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_non_avere_paura_tommaso_paradiso);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_cherofobia_martina_attili);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_it_s_my_life_bon_jovi);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_fear_for_nobody_maneskin);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_niente_paura_ligabue);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_six_forty_seven_instupendo);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_the_ghost_niviro);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_day_of_chaos_kevin_macleod);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_cradles_sub_urban);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.fear_main_titles_charlie_clouser);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
         */
    }

    public List<MusicCard> getFearCardList(List<MusicCard> cardItems){
        MusicCard card = null;

        card = new MusicCard(R.mipmap.image_music_green, "Non avere paura", "Tommaso Paradiso");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Cherofobia", "Martina Attili");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "It's my life", "Bon Jovi");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Fear for nobody", "Maneskin");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Niente paura", "Ligabue");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Six forty seven", "Instupendo");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "The Ghost", "NIVIRO");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Day of chaos", "Kevin Macleod");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Cradles", "Sub Urban");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Main Titles", "Charlie Clouser");
        cardItems.add(card);

        return cardItems;
    }



    @OptIn(markerClass = UnstableApi.class)
    public List<MediaItem> getHungryPlaylist(){
        MediaItem item = null;
        Uri uri = null;
        List<MediaItem> mediaItems = new ArrayList<MediaItem>();

        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_ma_che_bonta_mina.mp3?alt=media&token=5bf08fe4-082f-48b7-81d0-b7cd13d71222");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_gelato_al_cioccolato_pupo.mp3?alt=media&token=d59cfae6-ad79-43e4-a084-cc5d6563cc75");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_that_s_amore_dean_martin.mp3?alt=media&token=847de7ea-7f92-4299-872c-5ee2b63d9afe");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_sashimi_pinguini_tattici_nucleari.mp3?alt=media&token=0906cd47-f022-4a11-8ccd-e5f5c2827b06");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_lemon_tree_fools_garden.mp3?alt=media&token=73688b89-4a97-471d-8383-de2ba3b68062");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_marmellata_25_cesare_cremonini.mp3?alt=media&token=d9b2ba30-331e-4683-9604-abd433b6b01f");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_digsy_s_dinner_oasis.mp3?alt=media&token=10c079cf-6136-4ad3-84fa-c5f53f8cd8e3");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_dio_mio_no_lucio_battisti.mp3?alt=media&token=38ed9536-b4a5-4348-a6cb-cfb158bd3340");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_viva_la_pappa_col_pomodoro_rita_pavone.mp3?alt=media&token=6e83f4ec-1590-4031-9aa2-34b8510b07f4");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/inside-out-1653917127161.appspot.com/o/hungry_sweet_potato_sia.mp3?alt=media&token=7fad79f2-0966-4124-b6a6-64cab596e323");
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);

        return mediaItems;

        //In case of resources in raw directory (too much data in apk memory)
        /*
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_ma_che_bonta_mina);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_gelato_al_cioccolato_pupo);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_that_s_amore_dean_martin);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_sashimi_pinguini_tattici_nucleari);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_lemon_tree_fools_garden);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_marmellata_25_cesare_cremonini);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_digsy_s_dinner_oasis);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_dio_mio_no_lucio_battisti);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_viva_la_pappa_col_pomodoro_rita_pavone);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
        uri = RawResourceDataSource.buildRawResourceUri(R.raw.hungry_sweet_potato_sia);
        item = MediaItem.fromUri(uri);
        mediaItems.add(item);
         */
    }

    public List<MusicCard> getHungryCardList(List<MusicCard> cardItems){
        MusicCard card = null;

        card = new MusicCard(R.mipmap.image_music_green, "Ma che bontà", "Mina");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Gelato al cioccolato", "Pupo");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "That's amore", "Dean Martin");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Sashimi", "Pinguini Tattici Nucleari");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Lemon Tree", "Fools Garden");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Marmellata #25", "Cesare Cremonini");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Digsy's Dinner", "Oasis");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Dio mio no", "Lucio Battisti");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Viva la pappa col pomodoro", "Rita Pavone");
        cardItems.add(card);
        card = new MusicCard(R.mipmap.image_music_green, "Sweet Potato", "Sia");
        cardItems.add(card);

        return cardItems;
    }

}

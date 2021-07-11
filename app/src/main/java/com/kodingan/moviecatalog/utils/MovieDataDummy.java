package com.kodingan.moviecatalog.utils;


import com.kodingan.moviecatalog.data.source.local.entity.MovieEntity;


import com.kodingan.moviecatalog.data.source.local.entity.MovieWithId;
import com.kodingan.moviecatalog.data.source.remote.response.MovieResponse;


import java.util.ArrayList;

public class MovieDataDummy {

    public static ArrayList<MovieEntity> generateDummyMovies() {

        ArrayList<MovieEntity> movies = new ArrayList<>();

        movies.add(new MovieEntity("a14",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "21/11/2018",
                null,
                "https://drive.google.com/uc?id=1Xdagt7eNcXtirU84zm7KTTQp-4QjzZlK"));
        movies.add(new MovieEntity("a55",
                "Hewan Fantastis: Kejahatan Grindelwald ",
                "Gellert Grindelwald telah melarikan diri dari penjara dan telah mulai mengumpulkan pengikut ke tujuannya — meninggikan penyihir di atas semua makhluk non-magis. Satu-satunya yang bisa menghentikannya adalah penyihir yang pernah disebutnya sebagai sahabat terdekatnya, Albus Dumbledore. Namun, Dumbledore akan perlu mencari bantuan dari penyihir yang telah menggagalkan Grindelwald sebelumnya, mantan muridnya, Newt Scamander, yang setuju untuk membantu, tidak menyadari bahaya yang ada di depan. Garis-garis digambar saat cinta dan kesetiaan diuji, bahkan di antara teman-teman dan keluarga sejati, di dunia sihir yang semakin terbagi.",
                "16/11/2018",
                null,
                "https://drive.google.com/uc?id=1YUyyYcz3JC0N4a0q4BFoBbMoPSlL-33d"));
        movies.add(new MovieEntity("a47",    "Glass",
                " In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "18/01/2019",
                null,
                "https://drive.google.com/uc?id=1Lq7KagwYMQUH4GDNM28DhEI-lM3QQqV1"));
        movies.add(new MovieEntity("a74",
                "How to Train Your Dragon: The Hidden World",
                "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
                "22/02/2019",
                null,
                "https://drive.google.com/uc?id=11BeFrsovkMpl468FlGAYFSAIJr04kjrX"));
        movies.add(new MovieEntity("a51",
                "Avengers: Infinity War ",
                "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.",
                "27/04/2018",
                null,
                "https://drive.google.com/uc?id=1P8ryGAjQ24_InVBFRJLhiNZf0-wbOLgx"));
        movies.add(new MovieEntity("a94",
                "Alita: Battle Angel",
                "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. ",
                "14/02/2019",
                null,
                "https://drive.google.com/uc?id=1biUiBuE4oHdvW45ZEBby10WiSwmIC7Ph"));
        movies.add(new MovieEntity("a95",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "27/04/2018",
                null,
                "https://drive.google.com/uc?id=1Gek1YljmU6upe8jLed_hW6qiYfkUbAk4"));
        movies.add(new MovieEntity("a96",
                "Spiderman ",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "14/12/2018 ",
                null,
                "https://drive.google.com/uc?id=1PgCAEu95RvPYVyTqvrD6b1tBfdeKUpdp"));
        movies.add(new MovieEntity("a98",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "02/11/2018 ",
                null,
                "https://drive.google.com/uc?id=1GsYml6iM07iJX49HPZYDLFFEY-LpOIhd"));
        movies.add(new MovieEntity("a97",
                "Mortal Engines ",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "14/12/2018",
                null,
                "https://drive.google.com/uc?id=1muKyCHLLLoM1pZZlbrje3uNEIr98dNOx"));
        return movies;
    }



    public static ArrayList<MovieResponse> generateRemoteDummyMovies() {

        ArrayList<MovieResponse> movies = new ArrayList<>();

        movies.add(new MovieResponse("a14",
                "Creed II",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "21/11/2018",
                "https://drive.google.com/uc?id=1Xdagt7eNcXtirU84zm7KTTQp-4QjzZlK"));
        movies.add(new MovieResponse("a55",
                "Hewan Fantastis: Kejahatan Grindelwald ",
                "Gellert Grindelwald telah melarikan diri dari penjara dan telah mulai mengumpulkan pengikut ke tujuannya — meninggikan penyihir di atas semua makhluk non-magis. Satu-satunya yang bisa menghentikannya adalah penyihir yang pernah disebutnya sebagai sahabat terdekatnya, Albus Dumbledore. Namun, Dumbledore akan perlu mencari bantuan dari penyihir yang telah menggagalkan Grindelwald sebelumnya, mantan muridnya, Newt Scamander, yang setuju untuk membantu, tidak menyadari bahaya yang ada di depan. Garis-garis digambar saat cinta dan kesetiaan diuji, bahkan di antara teman-teman dan keluarga sejati, di dunia sihir yang semakin terbagi.",
                "16/11/2018",
                "https://drive.google.com/uc?id=1YUyyYcz3JC0N4a0q4BFoBbMoPSlL-33d"));
        movies.add(new MovieResponse("a47",
                "Glass",
                " In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "18/01/2019",
                "https://drive.google.com/uc?id=1Lq7KagwYMQUH4GDNM28DhEI-lM3QQqV1"));
        movies.add(new MovieResponse("a74",
                "How to Train Your Dragon: The Hidden World",
                "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
                "22/02/2019",
                "https://drive.google.com/uc?id=11BeFrsovkMpl468FlGAYFSAIJr04kjrX"));
        movies.add(new MovieResponse("a51",
                "Avengers: Infinity War ",
                "Karena Avengers dan sekutunya terus melindungi dunia dari ancaman yang terlalu besar untuk ditangani oleh seorang pahlawan, bahaya baru telah muncul dari bayangan kosmik: Thanos. Seorang lalim penghujatan intergalaksi, tujuannya adalah untuk mengumpulkan semua enam Batu Infinity, artefak kekuatan yang tak terbayangkan, dan menggunakannya untuk menimbulkan kehendak memutar pada semua realitas. Segala sesuatu yang telah diperjuangkan oleh Avengers telah berkembang hingga saat ini - nasib Bumi dan keberadaannya sendiri tidak pernah lebih pasti.",
                "27/04/2018",
                "https://drive.google.com/uc?id=1P8ryGAjQ24_InVBFRJLhiNZf0-wbOLgx"));
        movies.add(new MovieResponse("a94",
                "Alita: Battle Angel",
                "Ketika Alita terbangun tanpa ingatan tentang siapa dia di dunia masa depan yang tidak dia kenal, dia ditangkap oleh Ido, seorang dokter yang penuh kasih yang menyadari bahwa di suatu tempat dalam cangkang cyborg yang ditinggalkan ini adalah hati dan jiwa seorang wanita muda dengan luar biasa. ",
                "14/02/2019",
                "https://drive.google.com/uc?id=1biUiBuE4oHdvW45ZEBby10WiSwmIC7Ph"));
        movies.add(new MovieResponse("a95",
                "Aquaman",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "27/04/2018",
                "https://drive.google.com/uc?id=1Gek1YljmU6upe8jLed_hW6qiYfkUbAk4"));
        movies.add(new MovieResponse("a96",
                "Spiderman ",
                "Miles Morales is juggling his life between being a high school student and being a spider-man. When Wilson \"Kingpin\" Fisk uses a super collider, others from across the Spider-Verse are transported to this dimension.",
                "14/12/2018 ",
                "https://drive.google.com/uc?id=1PgCAEu95RvPYVyTqvrD6b1tBfdeKUpdp"));
        movies.add(new MovieResponse("a98",
                "Bohemian Rhapsody",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "02/11/2018 ",
                "https://drive.google.com/uc?id=1GsYml6iM07iJX49HPZYDLFFEY-LpOIhd"));
        movies.add(new MovieResponse("a97",
                "Mortal Engines ",
                "Many thousands of years in the future, Earth’s cities roam the globe on huge wheels, devouring each other in a struggle for ever diminishing resources. On one of these massive traction cities, the old London, Tom Natsworthy has an unexpected encounter with a mysterious young woman from the wastelands who will change the course of his life forever.",
                "14/12/2018",
                "https://drive.google.com/uc?id=1muKyCHLLLoM1pZZlbrje3uNEIr98dNOx"));
        return movies;
    }



    public static MovieWithId generateDummyMovieWithId(MovieEntity movie, boolean bookmarked) {
        MovieWithId movieWithId = new MovieWithId();
        movieWithId.mMovie = movie;
        movieWithId.mMovie.setBookmarked(bookmarked);

        return movieWithId;
    }













}

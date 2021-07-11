package com.kodingan.moviecatalog.utils;

import com.kodingan.moviecatalog.data.source.local.entity.TvEntity;
import com.kodingan.moviecatalog.data.source.local.entity.TvWithId;
import com.kodingan.moviecatalog.data.source.remote.response.TvResponse;

import java.util.ArrayList;

public class TvDataDummy {

    public static ArrayList<TvEntity> generateDummyTvs() {

        ArrayList<TvEntity> tvs = new ArrayList<>();

        tvs.add(new TvEntity("a14",
                "Dragon Ball",
                "Trunks returns from the future to train with Goku and Vegeta. However, it disappears without warning. Then the mysterious Fu bursts in, telling them that Trunks has been imprisoned in the Prison Planet, a mysterious complex in an unknown place in the universes. The group seeks the dragon balls to free Trunks, but an endless battle awaits them! Will Goku and the others rescue Trunks and escape the Prison Planet?",
                "2018",
                null,
                "https://drive.google.com/uc?id=1tsy1s7SC2PebfnMR1bgM_yL7vhpx7dm-"));
        tvs.add(new TvEntity("a55",
                "Family Guy",
                "With the Griffins stuck again at home during a blackout, Peter tells the story of “Star Wars Episode VI: Return of the Jedi.",
                "22/05/2011",
                null,
                "https://drive.google.com/uc?id=1oRJwRDbN01geS26-NBnHddgWu-4J6zTK"));
        tvs.add(new TvEntity("a47",
                "Fairytale",
                "This series explores the gulf between parent and child and understanding the concept of what personal identity is. The narrative takes the familiar form of a fairy tale, expressing its ideas through rhyme, verse and whimsy.",
                "01/09/2018",
                null,
                "https://drive.google.com/uc?id=1ml2NAAFlxlgtn9QnbY_yx4CnpfCOtyFK"));
        tvs.add(new TvEntity("a74",
                "How To Train",
                "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
                "22/02/2019",
                null,
                "https://drive.google.com/uc?id=11BeFrsovkMpl468FlGAYFSAIJr04kjrX"));
        tvs.add(new TvEntity("a51",
                "The Arrow ",
                "Arrow adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
                "2012",
                null,
                "https://drive.google.com/uc?id=1NinAhVlazAIUMvL3XNNtk6-fs0I_wOwc"));
        tvs.add(new TvEntity("a93",
                "The Walking Dead: Red Machete",
                "Follow the path of the red-handled weapon used by Rick Grimes to kill Terminus leader Gareth; from its innocent beginnings on a hardware store shelf at the start of the apocalypse into the hands of various survivors, familiar and new.",
                "2017",
                null,
                "https://drive.google.com/uc?id=1p0djiQR4CW35gjQMzP3eJe461q3SQZOu"));
        tvs.add(new TvEntity("a94",
                "The Umbrella Academy ",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "2019",
                null,
                "https://drive.google.com/uc?id=1owsDUEx5vWRTLQhMO4TK6k3FI32qy4H6"));
        tvs.add(new TvEntity("a95",
                "The Simpsons ",
                "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
                "1989",
                null,
                "https://drive.google.com/uc?id=1DEwD2g6AZn2rKtHkS4BSpGA9qZ3az47l"));
        tvs.add(new TvEntity("a96",
                "Supernatural ",
                "Dua bersaudara mencari ayah mereka yang hilang, pria yang melatih mereka untuk menjadi prajurit melawan kejahatan supernatural",
                "2005",
                null,
                "https://drive.google.com/uc?id=1dL7azTjWNmutAPWhmjF_-dtan64V3sUq"));
        tvs.add(new TvEntity("a97",
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "2015",
                null,
                "https://drive.google.com/uc?id=168JGbaMDQ8I6z6X0MHqJtnj8LtbkA_-8"));

        return tvs;
    }



    public static ArrayList<TvResponse> generateRemoteDummyTvs() {

        ArrayList<TvResponse> tvs = new ArrayList<>();

        tvs.add(new TvResponse("a14",
                "Dragon Ball",
                "Trunks returns from the future to train with Goku and Vegeta. However, it disappears without warning. Then the mysterious Fu bursts in, telling them that Trunks has been imprisoned in the Prison Planet, a mysterious complex in an unknown place in the universes. The group seeks the dragon balls to free Trunks, but an endless battle awaits them! Will Goku and the others rescue Trunks and escape the Prison Planet?",
                "2018",
                "https://drive.google.com/uc?id=1tsy1s7SC2PebfnMR1bgM_yL7vhpx7dm-"));
        tvs.add(new TvResponse("a55",
                "Family Guy",
                "With the Griffins stuck again at home during a blackout, Peter tells the story of “Star Wars Episode VI: Return of the Jedi.",
                "22/05/2011",
                "https://drive.google.com/uc?id=1oRJwRDbN01geS26-NBnHddgWu-4J6zTK"));
        tvs.add(new TvResponse("a47",
                "Fairytale",
                "This series explores the gulf between parent and child and understanding the concept of what personal identity is. The narrative takes the familiar form of a fairy tale, expressing its ideas through rhyme, verse and whimsy.",
                "01/09/2018",
                "https://drive.google.com/uc?id=1ml2NAAFlxlgtn9QnbY_yx4CnpfCOtyFK"));
        tvs.add(new TvResponse("a74",
                "How To Train",
                "Ketika Hiccup memenuhi mimpinya untuk menciptakan utopia naga yang damai, penemuan Toothless 'dari pasangan yang tak teruji dan sukar ditangkap membuat Night Fury menjauh. Ketika bahaya meningkat di rumah dan pemerintahan Hiccup sebagai kepala desa diuji, baik naga dan pengendara harus membuat keputusan yang mustahil untuk menyelamatkan jenis mereka.",
                "22/02/2019",
                "https://drive.google.com/uc?id=11BeFrsovkMpl468FlGAYFSAIJr04kjrX"));
        tvs.add(new TvResponse("a51",
                "The Arrow ",
                "Arrow adalah menceritakan kembali petualangan dari legendaris DC pahlawan Green Arrow",
                "2012",
                "https://drive.google.com/uc?id=1NinAhVlazAIUMvL3XNNtk6-fs0I_wOwc"));
        tvs.add(new TvResponse("a93",
                "The Walking Dead: Red Machete",
                "Follow the path of the red-handled weapon used by Rick Grimes to kill Terminus leader Gareth; from its innocent beginnings on a hardware store shelf at the start of the apocalypse into the hands of various survivors, familiar and new.",
                "2017",
                "https://drive.google.com/uc?id=1p0djiQR4CW35gjQMzP3eJe461q3SQZOu"));
        tvs.add(new TvResponse("a94",
                "The Umbrella Academy ",
                "A dysfunctional family of superheroes comes together to solve the mystery of their father's death, the threat of the apocalypse and more.",
                "2019",
                "https://drive.google.com/uc?id=1owsDUEx5vWRTLQhMO4TK6k3FI32qy4H6"));
        tvs.add(new TvResponse("a95",
                "The Simpsons ",
                "Bertempat di Springfield, kota rata-rata di Amerika, pertunjukan ini berfokus pada kejenakaan dan petualangan sehari-hari keluarga Simpson; Homer, Marge, Bart, Lisa dan Maggie, serta ribuan pemain virtual. Sejak awal, serial ini telah menjadi ikon budaya pop, menarik ratusan selebriti menjadi bintang tamu. Acara ini juga menjadi terkenal karena satirnya yang tak kenal takut terhadap kehidupan politik, media, dan Amerika secara umum.",
                "1989",
                "https://drive.google.com/uc?id=1DEwD2g6AZn2rKtHkS4BSpGA9qZ3az47l"));
        tvs.add(new TvResponse("a96",
                "Supernatural ",
                "Dua bersaudara mencari ayah mereka yang hilang, pria yang melatih mereka untuk menjadi prajurit melawan kejahatan supernatural",
                "2005",
                "https://drive.google.com/uc?id=1dL7azTjWNmutAPWhmjF_-dtan64V3sUq"));
        tvs.add(new TvResponse("a97",
                "Supergirl",
                "Twenty-four-year-old Kara Zor-El, who was taken in by the Danvers family when she was 13 after being sent away from Krypton, must learn to embrace her powers after previously hiding them. The Danvers teach her to be careful with her powers, until she has to reveal them during an unexpected disaster, setting her on her journey of heroism.",
                "2015",
                "https://drive.google.com/uc?id=168JGbaMDQ8I6z6X0MHqJtnj8LtbkA_-8"));
        return tvs;
    }





    public static TvWithId generateDummyTvWithId(TvEntity movie, boolean bookmarked) {
        TvWithId tvWithId = new TvWithId();
        tvWithId.mTv = movie;
        tvWithId.mTv.setBookmarked(bookmarked);

        return tvWithId;
    }



}

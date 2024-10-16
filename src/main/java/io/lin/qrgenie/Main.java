package io.lin.qrgenie;

import io.lin.qrgenie.datahandler.ExcelService;
import io.lin.qrgenie.datahandler.FileService;
import io.lin.qrgenie.datahandler.JSONService;
import io.lin.qrgenie.datahandler.QRService;
import io.lin.qrgenie.imagehandler.ImageService;
import io.nayuki.qrcodegen.QrCode;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        char type, mode;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.print("Regular(r) / VIP(v): ");
            type = sc.next().charAt(0);
            System.out.print("On-site(s) / Online(l): ");
            mode = sc.next().charAt(0);
        } while((type != 'r' && type != 'v') || (mode != 's' && mode != 'l'));

        String registrationType, modeOfRegistration;
        QRService qrService = new QRService();
        int colorLight, colorDark;
        String csvFilename;                                            // r = regular, v = vip | s = on-site, l = online
        String ticketTemplatePath, ticketTypePath, ticketModePath;
        if(type == 'r') {
            ticketTemplatePath = "regtix.png";
            ticketTypePath = "reg/";

//            registrationType = "Regular";
            colorLight = 0xFFFFFF;
            colorDark = 0x000000;

            if(mode == 's') {
                modeOfRegistration = "On-site";
                csvFilename = "rs-list.xlsx";
                ticketModePath = "onsite/";
            }
            else {
                modeOfRegistration = "Online";
                csvFilename = "rl-list.xlsx";
                ticketModePath = "online/";
            }
        } else {
            ticketTemplatePath = "viptix.png";
            ticketTypePath = "vip/";

//            registrationType = "VIP";
            colorLight = 0xFEB904;
            colorDark = 0x0E0C10;

            if(mode == 's') {
                modeOfRegistration = "On-site";
                csvFilename = "vs-list.xlsx";
                ticketModePath = "onsite/";
            }
            else {
                modeOfRegistration = "Online";
                csvFilename = "vl-list.xlsx";
                ticketModePath = "online/";
            }
        }

        qrService.setColors(colorDark, colorLight);

        String rootPath = "user/";
        FileService.setCsvPath(rootPath, csvFilename);
        System.out.println("Using: " + FileService.getCsvPath());

        ExcelService excelService = new ExcelService();

        List<Participant> participants = excelService.generateList();
        for(Participant p : participants) {
//            p.registrationType = registrationType;
            p.modeOfRegistration = modeOfRegistration;

            System.out.println(p);
        }

        String json;
        QrCode qr;
        BufferedImage image;

        ImageService imageService = new ImageService(rootPath, ticketTemplatePath, ticketTypePath, ticketModePath);

        int i = 0;
        FileService currentFileImage = new FileService();
        while (i < participants.size()) {
            currentFileImage.appendParticipantIDNumberAndEmail(participants.get(i), i+1);
            currentFileImage.appendExtensionPng();

            json = JSONService.serializer(participants.get(i));
            qr = qrService.generateQr(json);
            image = qrService.convertToPng(qr);

            imageService.insertQrImageToTicket(image, currentFileImage.getQrFilename(), 2690, 37, 170,170);

            currentFileImage.reset();
            ++i;
        }
    }
}
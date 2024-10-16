package io.lin.qrgenie.datahandler;

import io.lin.qrgenie.Participant;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class FileService {
    private String qrFilename = "Ticket ID-";
    private static String csvPath = "rl-list.xlsx";

    public static String getCsvPath() {
        return csvPath;
    }
    public static void setCsvPath(String root, String filepath) {
        csvPath = root + filepath;
    }

    public String getQrFilename() {
        return this.qrFilename;
    }
    public void reset() {
        qrFilename = "Ticket ID-";
    }

    public void appendParticipantIDNumberAndEmail(Participant participant, int index) {
        this.qrFilename = index + " " + this.qrFilename + participant.studentNumber + " " + participant.email;
    }

    public void writeSvgToFile(String svg) {
        File svgFile = new File(qrFilename);
        try {
            Files.writeString(svgFile.toPath(), svg);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void appendExtensionSvg() {
        String extension = ".svg";
        qrFilename += extension;
    }
    public void appendExtensionPng() {
        String extension = ".png";
        qrFilename += extension;
    }
}

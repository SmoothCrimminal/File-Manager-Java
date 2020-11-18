import javax.swing.*;
        import javax.swing.table.DefaultTableModel;
        import java.awt.datatransfer.DataFlavor;
        import java.awt.event.*;
        import java.io.*;
        import java.nio.file.*;
        import java.nio.file.attribute.BasicFileAttributes;
        import java.text.DateFormat;
        import java.text.SimpleDateFormat;
        import java.util.*;

// odwiedzanie folderów i kopiowanie zawartości
class CopyFileVisitor extends SimpleFileVisitor<Path> {
    private final Path targetPath;
    private Path sourcePath = null;

    public CopyFileVisitor(Path targetPath) {
        this.targetPath = targetPath;
    }

    @Override
    public FileVisitResult preVisitDirectory(final Path dir,
                                             final BasicFileAttributes attrs) throws IOException {
        if (sourcePath == null) {
            sourcePath = dir;
        } else {
            Files.createDirectories(targetPath.resolve(sourcePath
                    .relativize(dir)));
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult visitFile(final Path file,
                                     final BasicFileAttributes attrs) throws IOException {
        Files.copy(file,
                targetPath.resolve(sourcePath.relativize(file)));
        return FileVisitResult.CONTINUE;
    }
}
// ********************************************************************************************

public class Main extends JFrame implements MouseListener, KeyListener, ActionListener {

    void copyPaste(File source, File dest) throws IOException {
        File checkList[] = source.listFiles();
        if (source.isFile() || checkList.length < 2) {
            if (!source.toPath().equals(dest.toPath())) {
                Files.copy(source.toPath(), dest.toPath());
                update(path2, 2);
            } else {
                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno nadpisać plik " + source.getName(), "", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION)
                    Files.copy(source.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING);
                update(path2, 2);
            }
        } else if (source.isDirectory()) {
            if (!source.toPath().equals(dest.toPath())) {
                Files.walkFileTree(source.toPath(), new CopyFileVisitor(dest.toPath()));
                update(path2, 2);
            } else{
                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno nadpisać folder " + source.getName(), "", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION)
                    Files.walkFileTree(source.toPath(), new CopyFileVisitor(dest.toPath()));
                update(path2, 2);
            }
        }
    }

    void sortbyColumn1(String[][] dataToSort, int col, int tableNum, String path) {
        String temp;
        String temp2;
        if (col == 0 && path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 0; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][0].compareToIgnoreCase(dataToSort[j + 1][0]) > 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        } else if (col == 1 && path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 0; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][1].compareToIgnoreCase(dataToSort[j + 1][1]) > 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        } else if (col == 0 && !path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 1; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][0].compareToIgnoreCase(dataToSort[j + 1][0]) > 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        } else if (col == 1 && !path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 1; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][1].compareToIgnoreCase(dataToSort[j + 1][1]) > 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(dataToSort, column);
        if (tableNum == 1)
            table1.setModel(tableModel);
        else
            table2.setModel(tableModel);
    }

    void sortbyColumn2(String[][] dataToSort, int col, int tableNum, String path) {
        String temp;
        String temp2;
        if (col == 0 && path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 0; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][0].compareToIgnoreCase(dataToSort[j + 1][0]) < 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        } else if (col == 1 && path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 0; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][1].compareToIgnoreCase(dataToSort[j + 1][1]) < 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        } else if (col == 0 && !path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 1; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][0].compareToIgnoreCase(dataToSort[j + 1][0]) < 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        } else if (col == 1 && !path.equals("C:\\")) {
            for (int i = 0; i < dataToSort.length - 1; i++) {
                for (int j = 1; j < dataToSort.length - i - 1; j++) {
                    if (dataToSort[j][1].compareToIgnoreCase(dataToSort[j + 1][1]) < 0) {
                        temp = dataToSort[j][0];
                        temp2 = dataToSort[j][1];
                        dataToSort[j][0] = dataToSort[j + 1][0];
                        dataToSort[j][1] = dataToSort[j + 1][1];
                        dataToSort[j + 1][0] = temp;
                        dataToSort[j + 1][1] = temp2;
                    }

                }
            }
        }
        DefaultTableModel tableModel = new DefaultTableModel(dataToSort, column);
        if (tableNum == 1)
            table1.setModel(tableModel);
        else
            table2.setModel(tableModel);
    }


    void sortSend(boolean sortingType, String headerName, int tableNumber) {
        if (sortingType == false && headerName.equals("Name") && tableNumber == 1) {
            sortbyColumn1(data, 0, tableNumber, path);
        } else if (sortingType == false && headerName.equals("Date") && tableNumber == 1) {
            sortbyColumn1(data, 1, tableNumber, path);
        } else if (sortingType == true && headerName.equals("Name") && tableNumber == 1) {
            sortbyColumn2(data, 0, tableNumber, path);
        } else if (sortingType == true && headerName.equals("Date") && tableNumber == 1) {
            sortbyColumn2(data, 1, tableNumber, path);
        } else if (sortingType == false && headerName.equals("Name") && tableNumber == 2) {
            sortbyColumn1(data3, 0, tableNumber, path2);
        } else if (sortingType == false && headerName.equals("Date") && tableNumber == 2) {
            sortbyColumn1(data3, 1, tableNumber, path2);
        } else if (sortingType == true && headerName.equals("Name") && tableNumber == 2) {
            sortbyColumn2(data3, 0, tableNumber, path2);
        } else if (sortingType == true && headerName.equals("Date") && tableNumber == 2) {
            sortbyColumn2(data3, 1, tableNumber, path2);
        }
    }

    boolean deleteDirectory(File directoryToBeDeleted) {
        File[] allContents = directoryToBeDeleted.listFiles();
        if (allContents != null) {
            for (File file : allContents) {
                deleteDirectory(file);
            }
        }
        return directoryToBeDeleted.delete();
    }

    public String trimmer(String s) {
        s = s.replace("[", "");
        s = s.replace("]", "");
        return s;
    }

    public void update(String path, int tableNumber) {
        File file = new File(path);
        File[] fileList = file.listFiles();
        String data1[][] = new String[fileList.length + 1][2];
        String data2[][] = new String[fileList.length][2];

        if (path.equals("C:\\")) {
            for (int i = 0; i < fileList.length; i++) {
                if (fileList[i].isFile())
                    data2[i][0] = fileList[i].getName();
                if (fileList[i].isDirectory())
                    data2[i][0] = "[" + fileList[i].getName() + "]";
            }

            for (int i = 0; i < fileList.length; i++) {
                try {
                    BasicFileAttributes attr = Files.readAttributes(fileList[i].toPath(), BasicFileAttributes.class);
                    data2[i][1] = dateFormat.format(new Date(attr.creationTime().toMillis()));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        } else {
            data1[0][0] = "[..]";
            if (fileList.length == 1) {
                if (fileList[0].isDirectory())
                    data1[1][0] = "[" + fileList[0].getName() + "]";
                if (fileList[0].isFile())
                    data1[1][0] = fileList[0].getName();
                try {
                    BasicFileAttributes attr = Files.readAttributes(fileList[0].toPath(), BasicFileAttributes.class);
                    data1[1][1] = dateFormat.format(new Date(attr.creationTime().toMillis()));
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            } else {
                for (int i = 0; i < fileList.length; i++) {
                    if (fileList[i].isFile())
                        data1[i + 1][0] = fileList[i].getName();
                    if (fileList[i].isDirectory())
                        data1[i + 1][0] = "[" + fileList[i].getName() + "]";
                }

                for (int i = 0; i < fileList.length; i++) {
                    try {
                        BasicFileAttributes attr = Files.readAttributes(fileList[i].toPath(), BasicFileAttributes.class);
                        data1[i + 1][1] = dateFormat.format(new Date(attr.creationTime().toMillis()));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        if (!path.equals("C:\\")) {
            DefaultTableModel tableModel = new DefaultTableModel(data1, column);
            if (tableNumber == 1) {
                table1.setModel(tableModel);
                text1.setText(path);
                data = null;
                data = data1;
            } else if (tableNumber == 2) {
                table2.setModel(tableModel);
                text2.setText(path);
                data3 = null;
                data3 = data1;
            }
        } else {
            DefaultTableModel tableModel = new DefaultTableModel(data2, column);
            if (tableNumber == 1) {
                table1.setModel(tableModel);
                text1.setText(path);
                data = null;
                data = data2;
            } else if (tableNumber == 2) {
                table2.setModel(tableModel);
                text2.setText(path);
                data3 = null;
                data3 = data2;
            }
        }
    }

    String path = "C:\\";
    String path2 = "C:\\";
    File file = new File(path);
    File[] fileList = file.listFiles();
    String column[] = {"Name", "Date"};
    JTable table1, table2;
    JTextField text1, text2;
    JPopupMenu popup, popup2;
    JMenuItem pmDelete;
    JMenuItem pmCreate;
    JMenuItem pmDelete2;
    JMenuItem pmCreate2;
    Object strings[] = new String[2];
    DefaultTableModel tableModel;
    String data[][] = new String[fileList.length][2];
    String data3[][] = new String[fileList.length][2];
    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    JScrollPane sp1, sp2;
    boolean sortingByName1 = false;
    boolean sortingByDate1 = false;
    boolean sortingByName2 = false;
    boolean sortingByDate2 = false;

    Main() {
        setSize(1000, 600);
        setTitle("Total Commander");
        setLayout(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addMouseListener(this);

        // Wczytanie do data nazw plików
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile() && fileList[i] != null) {
                data[i][0] = fileList[i].getName();
                data3[i][0] = fileList[i].getName();
            }
            if (fileList[i].isDirectory() && fileList[i] != null) {
                data[i][0] = "[" + fileList[i].getName() + "]";
                data3[i][0] = "[" + fileList[i].getName() + "]";
            }
        }
        // ***********************************************************

        // Wczytanie do data daty plików
        for (int i = 0; i < fileList.length; i++) {
            try {
                BasicFileAttributes attr = Files.readAttributes(fileList[i].toPath(), BasicFileAttributes.class);
                data[i][1] = dateFormat.format(new Date(attr.creationTime().toMillis()));
                data3[i][1] = dateFormat.format(new Date(attr.creationTime().toMillis()));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        // ***********************************************************
        // dodanie przycisku delete po naciśnięciu ppm
        tableModel = new DefaultTableModel(data3, column);
        popup = new JPopupMenu();
        popup2 = new JPopupMenu();
        pmDelete = new JMenuItem("Delete");
        pmCreate = new JMenuItem("Create Folder");
        pmDelete2 = new JMenuItem("Delete");
        pmCreate2 = new JMenuItem("Create Folder");
        pmDelete.addActionListener(this);
        pmCreate.addActionListener(this);
        pmDelete2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                int row = table2.getSelectedRow();
                int rows[] = table2.getSelectedRows();
                boolean isRowSelected = false;
                if (row >= 0 || rows.length > 0)
                    isRowSelected = true;
                if (source == pmDelete2 && isRowSelected) {
                    if (rows.length > 1) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć " + rows.length + " pliki/ów?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION) {
                            for (int j : rows) {
                                if (path2.equals("C:\\")) {
                                    String pathToDelete = text2.getText() + data3[j][0];
                                    pathToDelete = trimmer(pathToDelete);
                                    File toDelete = new File(pathToDelete);
                                    if (toDelete.isDirectory())
                                        deleteDirectory(toDelete);
                                    else if (toDelete.isFile())
                                        toDelete.delete();
                                } else {
                                    String pathToDelete = text2.getText() + "\\" + data3[j][0];
                                    pathToDelete = trimmer(pathToDelete);
                                    File toDelete = new File(pathToDelete);
                                    if (toDelete.isDirectory())
                                        deleteDirectory(toDelete);
                                    else if (toDelete.isFile())
                                        toDelete.delete();
                                }
                            }
                        }
                        update(path2, 2);
                    } else if (isRowSelected && rows.length == 1) {
                        if (path2.equals("C:\\")) {
                            String pathToDelete = text2.getText() + data3[row][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isFile()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    toDelete.delete();
                            } else if (toDelete.isDirectory()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    deleteDirectory(toDelete);
                            }
                        } else {
                            String pathToDelete = text2.getText() + "\\" + data3[row][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isFile()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    toDelete.delete();
                            } else if (toDelete.isDirectory()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    deleteDirectory(toDelete);
                            }
                        }
                        update(path2, 2);
                    }
                }
            }
        });
        pmCreate2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object source = e.getSource();
                if (source == pmCreate2) {
                    int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz utworzyć folder?", "", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        String fileName = JOptionPane.showInputDialog(null, "Podaj nazwę folderu: ");
                        if (path2.equals("C:\\") && fileName != null) {
                            File file = new File(text2.getText() + fileName);
                            if (file.mkdir())
                                System.out.println("Udało się utworzyć folder!");
                            else
                                JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (fileName != null) {
                                File file = new File(text2.getText() + "\\" + fileName);
                                if (file.mkdir())
                                    System.out.println("Udało się utworzyć folder!");
                                else
                                    JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    update(path2, 2);
                }
            }
        });
        popup.add(pmDelete);
        popup.add(pmCreate);
        popup2.add(pmDelete2);
        popup2.add(pmCreate2);

        // ***************************************
        table1 = new JTable(data, column) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table1.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table1.setColumnSelectionAllowed(false);
        table1.setRowSelectionAllowed(true);
        table1.setComponentPopupMenu(popup);
        table1.setDropMode(DropMode.INSERT_ROWS);
        table2 = new JTable(data, column) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table2.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        table2.setColumnSelectionAllowed(false);
        table2.setRowSelectionAllowed(true);
        table2.setComponentPopupMenu(popup2);
        table1.setDragEnabled(true);
        table2.setDragEnabled(true);
        table2.setDropMode(DropMode.INSERT_ROWS);
        TransferHandler getTH = table1.getTransferHandler();
        // drag and drop
        sp1 = new JScrollPane(table1);
        sp2 = new JScrollPane(table2);
        text1 = new JTextField(path);
        text2 = new JTextField(path2);
        text2.setBounds(600, 50, 200, 30);
        text1.setBounds(50, 50, 200, 30);
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table1.getSelectedRow() != -1){
                    table1.setTransferHandler(getTH);
                    table2.setTransferHandler(new TransferHandler() {
                        public boolean canImport(TransferSupport support) {
                            if (!support.isDrop()) {
                                return false;
                            }

                            if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                                return false;
                            }
                            return true;
                        }

                        public boolean importData(TransferSupport support) {
                            if (!canImport(support)) {
                                return false;
                            }
                            int row2 = table1.getSelectedRow();
                            int rows[] = table1.getSelectedRows();
                            if (rows.length > 1){
                                Object strings2[][] = new String[rows.length][2];
                                for (int i=0; i<rows.length; i++) {
                                    for (int j = 0; j < 2; j++)
                                        strings2[i][j] = table1.getValueAt(rows[i], j);
                                }
                                String pathToCopy, pathToPaste;
                                for (int k=0; k<strings2.length; k++) {
                                    if (path.equals("C:\\"))
                                        pathToCopy = text1.getText() + trimmer(strings2[k][0].toString());
                                    else
                                        pathToCopy = text1.getText() + "\\" + trimmer(strings2[k][0].toString());
                                    if (path2.equals("C:\\"))
                                        pathToPaste = path2 + trimmer(strings2[k][0].toString());
                                    else
                                        pathToPaste = text2.getText() + "\\" + trimmer(strings2[k][0].toString());
                                    File toCopy = new File(pathToCopy);
                                    File toPaste = new File(pathToPaste);
                                    try {
                                        copyPaste(toCopy, toPaste);
                                    } catch (IOException e) {
                                        System.out.println("Nie można skopiować pliku!");
                                    }
                                }
                                return true;
                            }
                            else {
                                if (row2 != -1) {
                                    for (int i = 0; i < 2; i++)
                                        strings[i] = table1.getValueAt(row2, i);
                                }
                                String pathToCopy, pathToPaste;
                                if (path.equals("C:\\"))
                                    pathToCopy = text1.getText() + trimmer(strings[0].toString());
                                else
                                    pathToCopy = text1.getText() + "\\" + trimmer(strings[0].toString());
                                if (path2.equals("C:\\"))
                                    pathToPaste = path2 + trimmer(strings[0].toString());
                                else
                                    pathToPaste = text2.getText() + "\\" + trimmer(strings[0].toString());
                                File toCopy = new File(pathToCopy);
                                File toPaste = new File(pathToPaste);
                                try {
                                    copyPaste(toCopy, toPaste);
                                } catch (IOException e) {
                                    System.out.println("Nie można skopiować pliku!");
                                }
                                return true;
                            }
                        }
                    });
                }
                if (e.getClickCount() == 2) {
                    int row = table1.getSelectedRow();
                    String fileName = data[row][0];
                    if (fileName.equals("[..]")) {
                        path = text1.getText();
                        File id = new File(path);
                        path = id.getParent();
                        update(path, 1);
                    } else {
                        fileName = trimmer(fileName);
                        if (text1.getText() != "C:\\")
                            path = text1.getText() + "\\" + fileName;
                        else
                            path = text1.getText() + fileName;
                        File id = new File(path);
                        if (id.isDirectory()) {
                            path = id.getAbsolutePath();
                            update(path, 1);
                        }
                    }
                }
            }
        });
        table2.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (table2.getSelectedRow() != -1){
                    table2.setTransferHandler(getTH);
                    table1.setTransferHandler(new TransferHandler() {
                        public boolean canImport(TransferSupport support) {
                            if (!support.isDrop()) {
                                return false;
                            }

                            if (!support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                                return false;
                            }
                            return true;
                        }

                        public boolean importData(TransferSupport support) {
                            if (!canImport(support)) {
                                return false;
                            }
                            int row2 = table2.getSelectedRow();
                            int rows[] = table2.getSelectedRows();
                            if (rows.length > 1){
                                Object strings2[][] = new String[rows.length][2];
                                for (int i=0; i<rows.length; i++) {
                                    for (int j = 0; j < 2; j++)
                                        strings2[i][j] = table2.getValueAt(rows[i], j);
                                }
                                String pathToCopy, pathToPaste;
                                for (int k=0; k<strings2.length; k++) {
                                    if (path2.equals("C:\\"))
                                        pathToCopy = text2.getText() + trimmer(strings2[k][0].toString());
                                    else
                                        pathToCopy = text2.getText() + "\\" + trimmer(strings2[k][0].toString());
                                    if (path.equals("C:\\"))
                                        pathToPaste = path + trimmer(strings2[k][0].toString());
                                    else
                                        pathToPaste = text1.getText() + "\\" + trimmer(strings2[k][0].toString());
                                    File toCopy = new File(pathToCopy);
                                    File toPaste = new File(pathToPaste);
                                    try {
                                        copyPaste(toCopy, toPaste);
                                    } catch (IOException e) {
                                        System.out.println("Nie można skopiować pliku!");
                                    }
                                }
                                return true;
                            }
                            else {
                                if (row2 != -1) {
                                    for (int i = 0; i < 2; i++)
                                        strings[i] = table2.getValueAt(row2, i);
                                }
                                String pathToCopy, pathToPaste;
                                if (path2.equals("C:\\"))
                                    pathToCopy = text2.getText() + trimmer(strings[0].toString());
                                else
                                    pathToCopy = text2.getText() + "\\" + trimmer(strings[0].toString());
                                if (path.equals("C:\\"))
                                    pathToPaste = path + trimmer(strings[0].toString());
                                else
                                    pathToPaste = text1.getText() + "\\" + trimmer(strings[0].toString());
                                File toCopy = new File(pathToCopy);
                                File toPaste = new File(pathToPaste);
                                try {
                                    copyPaste(toCopy, toPaste);
                                } catch (IOException e) {
                                    System.out.println("Nie można skopiować pliku!");
                                }
                                return true;
                            }
                        }
                    });
                }
                if (e.getClickCount() == 2) {
                    int row = table2.getSelectedRow();
                    String fileName = data3[row][0];
                    if (fileName.equals("[..]")) {
                        path2 = text2.getText();
                        File id = new File(path2);
                        path2 = id.getParent();
                        update(path2, 2);
                    } else {
                        fileName = trimmer(fileName);
                        if (text2.getText() != "C:\\")
                            path2 = text2.getText() + "\\" + fileName;
                        else
                            path2 = text2.getText() + fileName;
                        File id = new File(path2);
                        if (id.isDirectory()) {
                            path2 = id.getAbsolutePath();
                            update(path2, 2);
                        }
                    }
                }
            }
        });
        // ***************************************
        table2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                int row = table2.getSelectedRow();
                int rows[] = table2.getSelectedRows();
                boolean isRowSelected = false;
                if (row >= 0 || rows.length > 0)
                    isRowSelected = true;
                if (keyCode == 119 && isRowSelected) {
                    if (rows.length > 1) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć " + rows.length + " pliki/ów?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION) {
                            for (int j : rows) {
                                if (path2.equals("C:\\")) {
                                    String pathToDelete = text2.getText() + data3[j][0];
                                    pathToDelete = trimmer(pathToDelete);
                                    File toDelete = new File(pathToDelete);
                                    if (toDelete.isDirectory())
                                        deleteDirectory(toDelete);
                                    else if (toDelete.isFile())
                                        toDelete.delete();
                                } else {
                                    String pathToDelete = text2.getText() + "\\" + data3[j][0];
                                    pathToDelete = trimmer(pathToDelete);
                                    File toDelete = new File(pathToDelete);
                                    if (toDelete.isDirectory())
                                        deleteDirectory(toDelete);
                                    else if (toDelete.isFile())
                                        toDelete.delete();
                                }
                            }
                        }
                        update(path2, 2);
                    } else if (isRowSelected && rows.length == 1) {
                        if (path2.equals("C:\\")) {
                            String pathToDelete = text2.getText() + data3[row][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isFile()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    toDelete.delete();
                            } else if (toDelete.isDirectory()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    deleteDirectory(toDelete);
                            }
                        } else {
                            String pathToDelete = text2.getText() + "\\" + data3[row][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isFile()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    toDelete.delete();
                            } else if (toDelete.isDirectory()) {
                                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data3[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                                if (ans == JOptionPane.YES_OPTION)
                                    deleteDirectory(toDelete);
                            }
                        }
                        update(path2, 2);
                    }
                }
                if (keyCode == 118) {
                    int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz utworzyć folder?", "", JOptionPane.YES_NO_OPTION);
                    if (ans == JOptionPane.YES_OPTION) {
                        String fileName = JOptionPane.showInputDialog(null, "Podaj nazwę folderu: ");
                        if (path.equals("C:\\") && fileName != null) {
                            File file = new File(text2.getText() + fileName);
                            if (file.mkdir())
                                System.out.println("Udało się utworzyć folder!");
                            else
                                JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                        } else {
                            if (fileName != null) {
                                File file = new File(text2.getText() + "\\" + fileName);
                                if (file.mkdir())
                                    System.out.println("Udało się utworzyć folder!");
                                else
                                    JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                    }
                    update(path2, 2);
                }
            }
        });
        // Sortowanie tabeli
        table1.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table1.columnAtPoint(e.getPoint());
                String name = table1.getColumnName(column);
                if (name.equals("Name") && sortingByName1 == false) {
                    sortSend(sortingByName1, "Name", 1);
                    sortingByName1 = true;
                } else if (name.equals("Name") && sortingByName1 == true) {
                    sortSend(sortingByName1, "Name", 1);
                    sortingByName1 = false;
                } else if (name.equals("Date") && sortingByDate1 == false) {
                    sortSend(sortingByDate1, "Date", 1);
                    sortingByDate1 = true;
                } else if (name.equals("Date") && sortingByDate1 == true) {
                    sortSend(sortingByDate1, "Date", 1);
                    sortingByDate1 = false;
                }

            }
        });
        table2.getTableHeader().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int column = table2.columnAtPoint(e.getPoint());
                String name = table2.getColumnName(column);
                if (name.equals("Name") && sortingByName2 == false) {
                    sortSend(sortingByName2, "Name", 2);
                    sortingByName2 = true;
                } else if (name.equals("Name") && sortingByName2 == true) {
                    sortSend(sortingByName2, "Name", 2);
                    sortingByName2 = false;
                } else if (name.equals("Date") && sortingByDate2 == false) {
                    sortSend(sortingByDate2, "Date", 2);
                    sortingByDate2 = true;
                } else if (name.equals("Date") && sortingByDate2 == true) {
                    sortSend(sortingByDate2, "Date", 2);
                    sortingByDate2 = false;
                }
            }
        });
        table1.addKeyListener(this);
        sp1.setBounds(0, 100, 400, 400);
        sp2.setBounds(500, 100, 400, 400);
        add(sp1);
        add(sp2);
        add(text1);
        add(text2);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Main();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 1) {
            update(path, 1);
            update(path2, 2);
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        int row = table1.getSelectedRow();
        int rows[] = table1.getSelectedRows();
        boolean isRowSelected = false;
        if (row >= 0 || rows.length > 0)
            isRowSelected = true;
        if (keyCode == 119 && isRowSelected) {
            if (rows.length > 1) {
                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć " + rows.length + " pliki/ów?", "", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    for (int j : rows) {
                        if (path.equals("C:\\")) {
                            String pathToDelete = text1.getText() + data[j][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isDirectory())
                                deleteDirectory(toDelete);
                            else if (toDelete.isFile())
                                toDelete.delete();
                        } else {
                            String pathToDelete = text1.getText() + "\\" + data[j][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isDirectory())
                                deleteDirectory(toDelete);
                            else if (toDelete.isFile())
                                toDelete.delete();
                        }
                    }
                }
                update(path, 1);
            } else if (isRowSelected && rows.length == 1) {
                if (path.equals("C:\\")) {
                    String pathToDelete = text1.getText() + data[row][0];
                    pathToDelete = trimmer(pathToDelete);
                    File toDelete = new File(pathToDelete);
                    if (toDelete.isFile()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            toDelete.delete();
                    } else if (toDelete.isDirectory()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            deleteDirectory(toDelete);
                    }
                } else {
                    String pathToDelete = text1.getText() + "\\" + data[row][0];
                    pathToDelete = trimmer(pathToDelete);
                    File toDelete = new File(pathToDelete);
                    if (toDelete.isFile()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            toDelete.delete();
                    } else if (toDelete.isDirectory()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            deleteDirectory(toDelete);
                    }
                }
                update(path, 1);
            }
        }
        if (keyCode == 118) {
            int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz utworzyć folder?", "", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                String fileName = JOptionPane.showInputDialog(null, "Podaj nazwę folderu: ");
                if (path.equals("C:\\") && fileName != null) {
                    File file = new File(text1.getText() + fileName);
                    if (file.mkdir())
                        System.out.println("Udało się utworzyć folder!");
                    else
                        JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (fileName != null) {
                        File file = new File(text1.getText() + "\\" + fileName);
                        if (file.mkdir())
                            System.out.println("Udało się utworzyć folder!");
                        else
                            JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            update(path, 1);
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        int row = table1.getSelectedRow();
        int rows[] = table1.getSelectedRows();
        boolean isRowSelected = false;
        if (row >= 0 || rows.length > 0)
            isRowSelected = true;
        if (source == pmDelete && isRowSelected) {
            if (rows.length > 1) {
                int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć " + rows.length + " pliki/ów?", "", JOptionPane.YES_NO_OPTION);
                if (ans == JOptionPane.YES_OPTION) {
                    for (int j : rows) {
                        if (path.equals("C:\\")) {
                            String pathToDelete = text1.getText() + data[j][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isDirectory())
                                deleteDirectory(toDelete);
                            else if (toDelete.isFile())
                                toDelete.delete();
                        } else {
                            String pathToDelete = text1.getText() + "\\" + data[j][0];
                            pathToDelete = trimmer(pathToDelete);
                            File toDelete = new File(pathToDelete);
                            if (toDelete.isDirectory())
                                deleteDirectory(toDelete);
                            else if (toDelete.isFile())
                                toDelete.delete();
                        }
                    }
                }
                update(path, 1);
            } else if (isRowSelected && rows.length == 1) {
                if (path.equals("C:\\")) {
                    String pathToDelete = text1.getText() + data[row][0];
                    pathToDelete = trimmer(pathToDelete);
                    File toDelete = new File(pathToDelete);
                    if (toDelete.isFile()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            toDelete.delete();
                    } else if (toDelete.isDirectory()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            deleteDirectory(toDelete);
                    }
                } else {
                    String pathToDelete = text1.getText() + "\\" + data[row][0];
                    pathToDelete = trimmer(pathToDelete);
                    File toDelete = new File(pathToDelete);
                    if (toDelete.isFile()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć plik o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            toDelete.delete();
                    } else if (toDelete.isDirectory()) {
                        int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno usunąć folder o nazwie: " + data[row][0] + "?", "", JOptionPane.YES_NO_OPTION);
                        if (ans == JOptionPane.YES_OPTION)
                            deleteDirectory(toDelete);
                    }
                }
                update(path, 1);
            }
        } else if (source == pmCreate) {
            int ans = JOptionPane.showConfirmDialog(null, "Czy na pewno chcesz utworzyć folder?", "", JOptionPane.YES_NO_OPTION);
            if (ans == JOptionPane.YES_OPTION) {
                String fileName = JOptionPane.showInputDialog(null, "Podaj nazwę folderu: ");
                if (path.equals("C:\\") && fileName != null) {
                    File file = new File(text1.getText() + fileName);
                    if (file.mkdir())
                        System.out.println("Udało się utworzyć folder!");
                    else
                        JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                } else {
                    if (fileName != null) {
                        File file = new File(text1.getText() + "\\" + fileName);
                        if (file.mkdir())
                            System.out.println("Udało się utworzyć folder!");
                        else
                            JOptionPane.showMessageDialog(null, "Folder o takiej nazwie już istnieje!", "", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            update(path, 1);
        }
    }
}
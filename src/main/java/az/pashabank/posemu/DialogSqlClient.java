package az.pashabank.posemu;

import az.pashabank.posemu.Database.DbObject;
import az.pashabank.posemu.Database.DbObjectType;
import az.pashabank.posemu.Database.DbSchema;
import java.awt.Frame;
import java.util.Enumeration;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class DialogSqlClient
        extends JDialog {

    private final EventLog log;

    private final Database db;

    private DefaultMutableTreeNode schemaNode;
    private DefaultMutableTreeNode tableNode;
    private DefaultMutableTreeNode indexNode;
    private DefaultMutableTreeNode viewNode;
    private DefaultMutableTreeNode triggerNode;

    public DialogSqlClient(Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        this.db = Database.getInstance();
        this.log = EventLog.getInstance();
        populateSchemaBrowser();
    }

    private DbTableMutableTreeNode searchTableNode(DefaultMutableTreeNode node, String nodeName) {
        //MutableTreeNode searchNode = null;
        DbTableMutableTreeNode searchNode = null;
        boolean exists = false;
        Enumeration e = node.breadthFirstEnumeration();
        Object o = null;
        //while (e.hasMoreElements()) {
//searchNode = new DbTableMutableTreeNode((DbObject)((DefaultMutableTreeNode)e.nextElement()));

        for (int i = 0; i < this.tSchemaBrowser.getModel().getChildCount(this.tableNode); i++) {
            DefaultMutableTreeNode tn = (DefaultMutableTreeNode)this.tSchemaBrowser.getModel().getChild(this.tableNode, i);
            //System.out.println(">>>> " + tn);
            //if (this.tSchemaBrowser.getModel().getChild(this.tableNode, i) instanceof DbTableMutableTreeNode) {
                DbTableMutableTreeNode tttt = (DbTableMutableTreeNode)this.tSchemaBrowser.getModel().getChild(this.tableNode, i);           

                if (tttt.toString().equals(nodeName)) {
                    searchNode = tttt;
                    break;
                }
                
                System.out.println(">>>> " + tttt);
            //}
            
        }

        




        return searchNode;
    }

    private void populateSchemaBrowser() {
        DefaultTreeModel model = (DefaultTreeModel) this.tSchemaBrowser.getModel();
        DefaultMutableTreeNode rootNode = (DefaultMutableTreeNode) model.getRoot();
        try {
            List<DbSchema> schemas = db.getDbSchemas();
            for (DbSchema schema : schemas) {
                this.schemaNode = new DefaultMutableTreeNode(schema.getName() + " - " + schema.getFile());
                rootNode.add(schemaNode);
            }
            this.tableNode = new DefaultMutableTreeNode("Tables");
            this.indexNode = new DefaultMutableTreeNode("Indexes");
            this.viewNode = new DefaultMutableTreeNode("Views");
            this.triggerNode = new DefaultMutableTreeNode("Triggers");
            this.schemaNode.add(this.tableNode);
            this.schemaNode.add(this.viewNode);
            this.schemaNode.add(this.triggerNode);
            this.schemaNode.add(this.indexNode);
            List<DbObject> objects = db.getDbTables();
            for (DbObject table : objects) {
                this.tableNode.add(new DbTableMutableTreeNode(table));
            }
            objects = db.getDbObjects();
            for (DbObject object : objects) {
                if (object.getType() == DbObjectType.TABLE) {
                    continue;
                }
                switch (object.getType()) {
                    case INDEX:
                        this.indexNode.add(new DbObjectMutableTreeNode(object));
                        break;
                    case TRIGGER:
                        this.triggerNode.add(new DbObjectMutableTreeNode(object));
                        break;
                    case VIEW:
                        this.viewNode.add(new DbObjectMutableTreeNode(object));
                        break;
                }
                if (object.getType() == DbObjectType.INDEX
                        || object.getType() == DbObjectType.TRIGGER) {
                    //DbObjectMutableTreeNode n = searchNode(this.tableNode, object.getTblName());
                    //((DbTableMutableTreeNode)n).addNode(object);

                    DbTableMutableTreeNode depTableNode = searchTableNode(this.tableNode, object.getTblName());

                    depTableNode.addNode(object);
                }

            }
        } catch (RuntimeException e) {
            log.error("Exception: " + e.getMessage(), e);
            JOptionPane.showMessageDialog(this, "Runtime exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Exception: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    class DbObjectMutableTreeNode
            extends DefaultMutableTreeNode {

        private final DbObject object;

        DbObjectMutableTreeNode(DbObject object) {
            super(object.getName());
            this.object = object;
        }

        DbObject getDbObject() {
            return this.object;
        }

    }

    public class DbTableMutableTreeNode
            extends DbObjectMutableTreeNode {

        private final DefaultMutableTreeNode indexNode;
        private final DefaultMutableTreeNode triggerNode;
        private final DefaultMutableTreeNode viewNode;

        DbTableMutableTreeNode(DbObject object) {
            super(object);
            this.indexNode = new DefaultMutableTreeNode("Indexes");
            this.triggerNode = new DefaultMutableTreeNode("Triggers");
            this.viewNode = new DefaultMutableTreeNode("Views");
            initialize();
        }

        private void initialize() {
            add(this.indexNode);
            add(this.triggerNode);
            add(this.viewNode);
        }

        void addNode(DbObject object) {
            DefaultMutableTreeNode node = new DefaultMutableTreeNode(object.getName());
            switch (object.getType()) {
                case INDEX:
                    this.indexNode.add(node);
                    break;
                case TRIGGER:
                    this.triggerNode.add(node);
                    break;
                case VIEW:
                    this.viewNode.add(node);
                    break;
                default:
                    break;
            }
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tSchemaBrowser = new javax.swing.JTree();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.tree.DefaultMutableTreeNode treeNode1 = new javax.swing.tree.DefaultMutableTreeNode("Database");
        tSchemaBrowser.setModel(new javax.swing.tree.DefaultTreeModel(treeNode1));
        jScrollPane1.setViewportView(tSchemaBrowser);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(739, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTree tSchemaBrowser;
    // End of variables declaration//GEN-END:variables
}

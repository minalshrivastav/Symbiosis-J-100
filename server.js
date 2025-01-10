const express = require('express');
const mysql = require('mysql');
const bodyParser = require('body-parser');
const cors = require('cors');

const app = express();
const port = 8090;

// Middleware
app.use(bodyParser.json());
app.use(cors());

// Database connection
const db = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'minalshri@2611',
  database: 'emedicine',
});

db.connect((err) => {
  if (err) {
    console.error('Database connection failed:', err);
    return;
  }
  console.log('Connected to the database.');
});

// API Endpoints

// Get all purchase orders
app.get('/api/purchase-orders', (req, res) => {
  const query = 'SELECT * FROM emedicine.purchase_orders';
  db.query(query, (err, results) => {
    if (err) {
      return res.status(500).json({ error: err });
    }
    res.json(results);
  });
});

// Add a new purchase order
app.post('/api/purchase-orders', (req, res) => {
  const { item, quantity, supplier, batchNo } = req.body;
  const query = 'INSERT INTO purchase_orders (item, quantity, supplier, batchNo,id) VALUES (?, ?, ?, ?,?)';
  db.query(query, [item, quantity, supplier, batchNo], (err, result) => {
    if (err) {
      return res.status(500).json({ error: err });
    }
    res.json({ message: 'Purchase order added successfully.', id: result.insertId });
  });
});

// Delete a purchase order
app.delete('/api/purchase-orders/:id', (req, res) => {
  const { id } = req.params;
  const query = 'DELETE FROM purchase_orders WHERE id = ?';
  db.query(query, [id], (err) => {
    if (err) {
      return res.status(500).json({ error: err });
    }
    res.json({ message: 'Purchase order deleted successfully.' });
  });
});

// Start the server
app.listen(port, () => {
  console.log(`Server running at http://localhost:8090`);
});

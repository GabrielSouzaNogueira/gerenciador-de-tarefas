import logo from './logo.svg';
import './App.css';
import Sidebar from './components/sidebar.js';
import MainContent from './components/sidebar.js/mainContent'; 
import Header from './components/header';

function App() {
    return (
        <div className="app-layout">
            <Sidebar />
            <main className="main-content-area">
                <Header />
                <MainContent />
            </main>
        </div>
    )
}

export default App;